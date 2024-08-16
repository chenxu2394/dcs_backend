package com.backend.ecommerce.application.mapper;

import com.backend.ecommerce.application.dto.CreateOrderProductDto;
import com.backend.ecommerce.domain.entities.OrderProduct;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface OrderProductMapper {
  @Mapping(target = "order.id", source = "uuid")
  @Mapping(target = "product.id", source = "source.product_id")
  @Mapping(target = "price", source = "source.price")
  OrderProduct toOrderProductFromCreateOrderDto(CreateOrderProductDto source, UUID uuid);
}
