package com.backend.ecommerce.application.dto;

import java.util.UUID;

//Todo: add the other fields
public record CreateProductRequest(String name, UUID categoryId){};
