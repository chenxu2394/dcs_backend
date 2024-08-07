package com.backend.ecommerce.infastructure.repositories;

import com.backend.ecommerce.domain.entities.Product;
import com.backend.ecommerce.domain.interfaces.IProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
