package com.backend.ecommerce.application.mapper;

import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderListDto;
import com.backend.ecommerce.application.dto.dtoInterfaces.ISingleOrderDto;
import com.backend.ecommerce.application.dto.order.OrderListDto;
import com.backend.ecommerce.application.dto.order.OrderUpdateDto;
import com.backend.ecommerce.application.dto.order.SingleOrderDto;
import com.backend.ecommerce.domain.entities.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface OrderMapper {
  OrderListDto toOrderListDto(IOrderListDto source);
  SingleOrderDto toSingleOrderDto(ISingleOrderDto source);
  Order toOrderFromUpdate(OrderUpdateDto source);
}
