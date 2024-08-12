package com.backend.ecommerce.application;

import com.backend.ecommerce.abstraction.OrderService;
import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.CreateOrderDto;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.CreatePaymentDto;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.OrderListDto;
import com.backend.ecommerce.application.dto.order.SingleOrderDto;
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

  @Override
  public List<OrderListDto> getAllOrders() {
    return jpaRepo.getAllOrders();
  }

  @Override
  public List<OrderListDto> getUsersOrders(String id){
    return jpaRepo.getUsersOrders(UUID.fromString(id));
  }

  @Override
  public List<OrderListDto> getAllOrdersByPaymentStatus(boolean status){
    return jpaRepo.getAllOrdersByPaymentStatus(status);
  }

  @Override
  public Optional<SingleOrderDto> findOrder(String id) {
    return jpaRepo.getSingleOrder(UUID.fromString(id));
  }

  @Override
  public Order createNewOrder(Order order) {
    return jpaRepo.save(order);
  }

  @Override
  public Optional<Order> updateOrder(Order order) {
    //Optional<Order> foundOrder = jpaRepo.findById(order.getId());
    //if (foundOrder.isEmpty()) return Optional.empty();
    //jpaRepo.save(order);
    return Optional.empty();
  }

  @Override
  public boolean deleteOrder(Integer id) {
    if (jpaRepo.findById(id).isPresent()) {
      jpaRepo.deleteById(id);
      return true;
    }
    return false;
  }

  @Transactional
  public Optional<SingleOrderDto> createNewOrder(String userInput){
    try {
      final Object uuid = UUID.randomUUID();
      JSONObject obj = new JSONObject(userInput);

      CreateOrderDto order = new CreateOrderDto(
              UUID.fromString(obj.getString("userId")),
              obj.getString("shipmentCity"),
              obj.getString("shipmentStreet"),
              obj.getString("shipmentPostNumber"),
              obj.getString("orderStatus"),
              Date.valueOf(obj.getString("orderDate"))
      );

      CreatePaymentDto payment = new CreatePaymentDto(
              UUID.fromString(uuid.toString()),
              obj.getBoolean("paymentStatus"),
              obj.getFloat("amount"),
              obj.getString("paymentCity"),
              obj.getString("paymentStreet"),
              obj.getString("paymentPostNumber")
      );

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
              "', '" + payment.paymentCity +
              "', '" + payment.paymentStreet +
              "','" + payment.getPaymentPostNumber +
              "'," + payment.paymentStatus + ");";

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
