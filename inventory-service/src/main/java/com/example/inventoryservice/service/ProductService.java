package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.ProductDto;
import com.example.inventoryservice.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product saveProduct(ProductDto productDto);

    Product updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);
}
