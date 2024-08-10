package com.backend.ecommerce.application.mapper;

import com.backend.ecommerce.domain.entities.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface OrderMapper {

}
