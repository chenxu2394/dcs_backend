package com.backend.ecommerce.application;

import ch.qos.logback.core.net.ObjectWriter;
import com.backend.ecommerce.abstraction.OrderService;
import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.CreateOrderDto;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.OrderListDto;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.SingleOrder;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaOrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.json.*;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.util.*;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  private JpaOrderRepository jpaRepo;

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
  public Optional<SingleOrder> findOrder(String id) {
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
  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private TransactionTemplate txTemplate;

  @Transactional
  public Optional<Object> getUsers(String teksti){
    final Object uuid = UUID.randomUUID();
    JSONObject obj = new JSONObject(teksti);

    CreateOrderDto order = new CreateOrderDto(
            UUID.fromString(obj.getString("userId")),
            obj.getString("shipmentCity"),
            obj.getString("shipmentStreet"),
            obj.getString("shipmentPostNumber"),
            obj.getString("orderStatus"),
            Date.valueOf(obj.getString("orderDate"))
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
    String newquery = secondQuery + parameters;
    Object[] insertParameters = productList.toList().stream().map(this::toObjectArray).flatMap(Stream::of).toArray(Object[]::new);

    txTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        jdbcTemplate.update(firstQuery);
        jdbcTemplate.update(newquery, insertParameters);
      }
    });

    //return jpaRepo.getTest(order, products);
    return Optional.empty();
  }


  private Object[] toObjectArray(Object object) {
    JSONObject json = new JSONObject(object.toString().replace("=",":"));
    System.out.println(json.getString("id")+ " " + json.getFloat("price"));
    return new Object[]{UUID.fromString(json.getString("id")), json.getFloat("price")};
  }
}
