package com.backend.ecommerce.application;

import com.backend.ecommerce.abstraction.OrderService;
import com.backend.ecommerce.application.dto.dtoInterfaces.ISingleOrderDto;
import com.backend.ecommerce.application.dto.order.OrderUpdateDto;
import com.backend.ecommerce.application.dto.product.ShortProductListDto;
import com.backend.ecommerce.application.mapper.OrderMapper;
import com.backend.ecommerce.application.dto.order.CreateOrderDto;
import com.backend.ecommerce.application.dto.order.CreatePaymentDto;
import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderListDto;
import com.backend.ecommerce.application.dto.order.SingleOrderDto;
import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaOrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.json.*;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.util.*;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  private JpaOrderRepository jpaRepo;
  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private TransactionTemplate txTemplate;
  ObjectMapper objectMapper = new ObjectMapper();
  @Autowired
  OrderMapper orderMapper;

  @Override
  public List<IOrderListDto> getAllOrders() {
    return jpaRepo.getAllOrders();
  }

  @Override
  public List<IOrderListDto> getUsersOrders(String id){
    return jpaRepo.getUsersOrders(UUID.fromString(id));
  }

  @Override
  public List<IOrderListDto> getAllOrdersByPaymentStatus(boolean status){
    return jpaRepo.getAllOrdersByPaymentStatus(status);
  }

  @Override
  public Optional<SingleOrderDto> findOrder(UUID id) {
    Optional<ISingleOrderDto> order = jpaRepo.getSingleOrder(id);
    return order.map(iSingleOrderDto -> orderMapper.toSingleOrderDto(iSingleOrderDto));
  }

  @Override
  public Optional<Order> updateOrder(UUID id, OrderUpdateDto orderUpdate) {
    Optional<ISingleOrderDto> foundOrder = jpaRepo.getSingleOrder(id);
    if (foundOrder.isEmpty()) return Optional.empty();

    Order order = new Order();
    Order mappedOrder = orderMapper.toOrderFromUpdate(orderUpdate);
    order.setId(id);
    order.setCity(mappedOrder.getCity());
    order.setStreet(mappedOrder.getStreet());
    order.setPost_number(mappedOrder.getPost_number());
    order.setStatus(mappedOrder.getStatus());

    jpaRepo.updateOrder(order);
    return Optional.of(order);
  }

  @Override
  public boolean deleteOrder(UUID id) {
    if (jpaRepo.getSingleOrder(id).isPresent()) {
      jpaRepo.deleteOrder(id);
      return true;
    }
    return false;
  }

  @Transactional
  public Optional<SingleOrderDto> createNewOrder(String userInput){
    try {
      final Object uuid = UUID.randomUUID();
      JSONObject obj = new JSONObject(userInput);

      //New Order object
      CreateOrderDto order = new CreateOrderDto(
              UUID.fromString(obj.getString("userId")),
              obj.getString("shipmentCity"),
              obj.getString("shipmentStreet"),
              obj.getString("shipmentPostNumber"),
              obj.getString("orderStatus"),
              Date.valueOf(obj.getString("orderDate"))
      );

      // New payment object
      CreatePaymentDto payment = new CreatePaymentDto(
              UUID.fromString(uuid.toString()),
              obj.getBoolean("paymentStatus"),
              obj.getFloat("amount"),
              obj.getString("billingCity"),
              obj.getString("billingStreet"),
              obj.getString("billingPostNumber")
      );

      //SQL queries
      JSONArray productList = obj.getJSONArray("products");
      String firstQuery = "INSERT INTO ecommerce.order (id, user_id, status, city, street, post_number, date)\n" +
              "VALUES ('"+ uuid +"', '"+ order.userId +
              "', '" + order.orderStatus +
              "', '" + order.shipmentCity +
              "', '" + order.shipmentStreet +
              "', '" + order.shipmentPostNumber +
              "', '" + order.orderDate +"');";

      String secondQuery = "INSERT INTO ecommerce.order_product (product_id, order_id, price) VALUES ";
      String param_base = "(?, '"+uuid+"', ?)";
      String parameters = String.join(",", Collections.nCopies(productList.length(), param_base));
      String newQuery = secondQuery + parameters;
      Object[] insertParameters = productList.toList().stream().map(this::toObjectArray).flatMap(Stream::of).toArray(Object[]::new);

      String thirdQuery = "INSERT INTO ecommerce.payment (order_id, amount, city, street, post_number, payment_status) VALUES " +
              "('"+payment.orderId +
              "', '"+ payment.amount +
              "', '" + payment.billingCity +
              "', '" + payment.billingStreet +
              "','" + payment.billingPostNumber +
              "'," + payment.paymentStatus + ");";

      // Exxecuting transaction
      txTemplate.execute(new TransactionCallbackWithoutResult() {
        @Override
        protected void doInTransactionWithoutResult(TransactionStatus status) {
          jdbcTemplate.update(firstQuery);
          jdbcTemplate.update(newQuery, insertParameters);
          jdbcTemplate.update(thirdQuery);
        }
      });

      obj.put("id", uuid);
      SingleOrderDto singleOrder = objectMapper.readValue(obj.toString(), SingleOrderDto.class);
      return Optional.of(singleOrder);
    } catch (Exception error) {
      System.out.println(error.toString());
      return Optional.empty();
    }
  }

  private Object[] toObjectArray(Object object) {
    JSONObject json = new JSONObject(object.toString().replace("=",":"));
    return new Object[]{UUID.fromString(json.getString("id")), json.getFloat("price")};
  }
}
