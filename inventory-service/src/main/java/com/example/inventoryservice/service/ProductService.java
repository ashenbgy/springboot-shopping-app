package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.ProductDto;
import com.example.inventoryservice.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product saveProduct(ProductDto productDto);

    Product updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);
}
