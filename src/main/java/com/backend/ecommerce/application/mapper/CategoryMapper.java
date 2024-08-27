package com.backend.ecommerce.application.mapper;

import com.backend.ecommerce.application.dto.product.category.CategoryDto;
import com.backend.ecommerce.domain.entities.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CategoryMapper {
    List<CategoryDto> toCategoryListDto(List<Category> categories);
}
