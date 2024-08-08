package com.backend.ecommerce.infastructure.jpaRepositories;

import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.dtoInterfaces.OrderListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Integer> {
  @Query(value= "SELECT o.id as Id, u.id as UserId, u.name as UserName, o.status as OrderStatus\n" +
          "FROM ecommerce.order AS o\n" +
          "INNER JOIN ecommerce.user AS u ON u.id = o.user_id;", nativeQuery = true) /* +
          "INNER JOIN ecommerce.payment AS p ON p.order_id = o.id;")*/
  public List<OrderListDto> getAllOrders();
}
