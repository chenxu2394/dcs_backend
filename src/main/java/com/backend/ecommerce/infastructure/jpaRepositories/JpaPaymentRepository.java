package com.backend.ecommerce.infastructure.jpaRepositories;

import com.backend.ecommerce.application.dto.payment.UpdatePaymentDto;
import com.backend.ecommerce.domain.entities.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface JpaPaymentRepository extends JpaRepository<Payment, UUID> {

  @Transactional
  @Modifying
  @Query(value = "UPDATE ecommerce.payment " +
          "SET payment_status = :#{#payment.paymentStatus}, amount = :#{#payment.amount}, " +
          "city = :#{#payment.city}, street = :#{#payment.street}, post_number = :#{#payment.postNumber} " +
          "WHERE id = :#{#payment.id}",
  nativeQuery = true)
  public void updatePayment(@Param("payment") Payment payment);
}
