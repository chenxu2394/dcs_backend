package com.backend.ecommerce.infastructure.jpaRepositories;

import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.OrderListDto;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.SingleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Integer> {
  @Query(value= "SELECT o.id as Id, u.id as UserId, u.name as UserName, " +
          "o.status as OrderStatus, p.payment_status as paymentStatus, p.amount as Amount\n" +
          "FROM ecommerce.order AS o\n" +
          "INNER JOIN ecommerce.user AS u ON u.id = o.user_id\n" +
          "INNER JOIN ecommerce.payment AS p ON p.order_id = o.id;", nativeQuery = true)
  public List<OrderListDto> getAllOrders();

  @Query(value = "SELECT o.id as Id, u.id as UserId, u.name as UserName, u.email as UserEmail, " +
          "p.id as PaymentId, o.status as OrderStatus, "+
          "o.city as ShipmentCity, o.street as ShipmentStreet, o.post_number as ShipmentPostNumber, " +
          "o.date as OrderDate, p.amount as Amount, p.payment_status as PaymentStatus, " +
          "p.city as BillingCity,p.street as BillingStreet, p.post_number as BillingPostNumber " +
          "FROM ecommerce.order AS o " +
          "INNER JOIN ecommerce.user AS u ON u.id = o.user_id " +
          "INNER JOIN ecommerce.payment AS p ON o.id = p.order_id " +
          "WHERE o.id = ?1;",
          nativeQuery = true)
  public Optional<SingleOrder>getSingleOrder(int id);


  @Query(value = "SELECT o.id as Id, u.id as UserId, u.name as UserName, " +
          "o.status as OrderStatus, p.payment_status as paymentStatus, p.amount as Amount\n" +
          "FROM ecommerce.order AS o\n" +
          "INNER JOIN ecommerce.user AS u ON u.id = o.user_id\n" +
          "INNER JOIN ecommerce.payment AS p ON p.order_id = o.id\n" +
          "WHERE p.payment_status = ?1;",
  nativeQuery = true)
  public List<OrderListDto> getAllOrdersByPaymentStatus(boolean status);
}
