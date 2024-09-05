package com.backend.ecommerce.application.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.backend.ecommerce.application.dto.user.ReturnedDto;
import com.backend.ecommerce.domain.entities.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {
  ReturnedDto toReturnedDto(User user);
}
