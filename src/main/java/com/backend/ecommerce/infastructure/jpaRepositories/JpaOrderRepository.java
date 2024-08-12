package com.backend.ecommerce.infastructure.jpaRepositories;

import com.backend.ecommerce.application.dto.dtoInterfaces.ISingleOrderDto;
import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderListDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Integer> {
  @Query(value = "SELECT o.id as Id, u.id as UserId, u.name as UserName, " +
          "o.status as OrderStatus, p.payment_status as paymentStatus, p.amount as Amount\n" +
          "FROM ecommerce.order AS o\n" +
          "INNER JOIN ecommerce.user AS u ON u.id = o.user_id\n" +
          "INNER JOIN ecommerce.payment AS p ON p.order_id = o.id;", nativeQuery = true)
  public List<IOrderListDto> getAllOrders();

  @Query(value = "SELECT o.id as Id, u.id as UserId, u.name as UserName, u.email as UserEmail, " +
          "p.id as PaymentId, o.status as OrderStatus, " +
          "o.city as ShipmentCity, o.street as ShipmentStreet, o.post_number as ShipmentPostNumber, " +
          "o.date as OrderDate, p.amount as Amount, p.payment_status as PaymentStatus, " +
          "p.city as BillingCity,p.street as BillingStreet, p.post_number as BillingPostNumber " +
          "FROM ecommerce.order AS o " +
          "INNER JOIN ecommerce.user AS u ON u.id = o.user_id " +
          "INNER JOIN ecommerce.payment AS p ON o.id = p.order_id " +
          "WHERE o.id = ?1;",
          nativeQuery = true)
  public Optional<ISingleOrderDto> getSingleOrder(UUID id);


  @Query(value = "SELECT o.id as Id, u.id as UserId, u.name as UserName, " +
          "o.status as OrderStatus, p.payment_status as paymentStatus, p.amount as Amount\n" +
          "FROM ecommerce.order AS o\n" +
          "INNER JOIN ecommerce.user AS u ON u.id = o.user_id\n" +
          "INNER JOIN ecommerce.payment AS p ON p.order_id = o.id\n" +
          "WHERE p.payment_status = ?1;",
          nativeQuery = true)
  public List<IOrderListDto> getAllOrdersByPaymentStatus(boolean status);

  @Query(value = "SELECT o.id as Id, u.id as UserId, u.name as UserName, " +
          "o.status as OrderStatus, p.payment_status as paymentStatus, p.amount as Amount\n" +
          "FROM ecommerce.order AS o\n" +
          "INNER JOIN ecommerce.user AS u ON u.id = o.user_id\n" +
          "INNER JOIN ecommerce.payment AS p ON p.order_id = o.id\n" +
          "WHERE u.id = ?1;",
          nativeQuery = true)
  public List<IOrderListDto> getUsersOrders(UUID id);

  @Transactional
  @Modifying
  @Query(value= "UPDATE ecommerce.order " +
          "SET status = :#{#order.status}, city = :#{#order.city}, street = :#{#order.street}, post_number = :#{#order.post_number} " +
          "WHERE id = :#{#order.id};",
          nativeQuery = true)
  public void updateOrder(@Param("order") Order order);

  @Transactional
  @Modifying
  @Query(value= "DELETE FROM ecommerce.order WHERE id = ?1",
          nativeQuery = true)
  public void deleteOrder(UUID id);
}
