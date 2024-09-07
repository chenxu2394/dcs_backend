package com.backend.ecommerce.application.mapper;

import com.backend.ecommerce.application.dto.user.UpdateUserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.backend.ecommerce.application.dto.user.ReturnedDto;
import com.backend.ecommerce.domain.entities.User;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

  @Mapping(target = "id", source = "id")
  User toUserFromUpdate(UpdateUserDto source);

  ReturnedDto toReturnedDto(User user);
}
