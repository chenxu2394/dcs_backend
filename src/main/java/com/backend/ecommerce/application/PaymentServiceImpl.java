package com.backend.ecommerce.application;

import com.backend.ecommerce.abstraction.PaymentService;
import com.backend.ecommerce.application.dto.payment.UpdatePaymentDto;
import com.backend.ecommerce.application.dto.payment.PaymentDto;
import com.backend.ecommerce.application.mapper.PaymentMapper;
import com.backend.ecommerce.domain.entities.Payment;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
  @Autowired
  JpaPaymentRepository jpaPaymentRepository;

  @Autowired
  PaymentMapper paymentMapper;


  @Override
  public List<PaymentDto> getAllPayments() {
    List<Payment> paymentList = jpaPaymentRepository.findAll();
    List<PaymentDto> paymentListDetailDtoList = new ArrayList<>();
    for (Payment payment : paymentList){
      paymentListDetailDtoList.add(paymentMapper.toPaymenDtoFromPayment(payment));
    }
    return paymentListDetailDtoList;
  }

  @Override
  public Optional<PaymentDto> updatePayment(UpdatePaymentDto updatePaymentDto, UUID uuid) {
    Optional<Payment> optionalPayment = jpaPaymentRepository.findById(uuid);
    if (optionalPayment.isEmpty()) return Optional.empty();
    Payment payment = optionalPayment.get();

    payment.setPaymentStatus(updatePaymentDto.paymentStatus());
    payment.setAmount(updatePaymentDto.amount());
    payment.setCity(updatePaymentDto.city());
    payment.setStreet(updatePaymentDto.street());
    payment.setPostNumber(updatePaymentDto.postNumber());

    jpaPaymentRepository.updatePayment(payment);
    return Optional.of(paymentMapper.toPaymenDtoFromPayment(payment));
  }
}
