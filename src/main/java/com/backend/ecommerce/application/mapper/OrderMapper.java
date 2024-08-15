package com.backend.ecommerce.application.mapper;

import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderDto;
import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderDetailsDto;
import com.backend.ecommerce.application.dto.order.CreateOrderDto;
import com.backend.ecommerce.application.dto.order.OrderDto;
import com.backend.ecommerce.application.dto.order.OrderUpdateDto;
import com.backend.ecommerce.application.dto.order.OrderDetailsDto;
import com.backend.ecommerce.domain.entities.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface OrderMapper {
  OrderDto toOrderListDto(IOrderDto source);
  OrderDetailsDto toOrderDetailsDtoFromInterface(IOrderDetailsDto source);
  Order toOrderFromUpdate(OrderUpdateDto source);

  @Mapping(target = "user.id", source = "source.user_id")
  @Mapping(target = "orderProducts", source = "source.products")
  Order toOrderFromCreateOrderDto(CreateOrderDto source);

  OrderDetailsDto toOrderDetailsDtoFromCreateOrderDto(CreateOrderDto source, UUID uuid);
}