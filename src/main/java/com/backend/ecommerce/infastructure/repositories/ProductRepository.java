package com.backend.ecommerce.infastructure.repositories;

import com.backend.ecommerce.domain.entities.Product;
import com.backend.ecommerce.domain.interfaces.IProductRepository;

import java.util.List;

public class ProductRepository implements IProductRepository {

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
