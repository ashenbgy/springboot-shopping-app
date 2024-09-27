package com.example.inventoryservice.service.serviceImpl;

import com.example.inventoryservice.entity.Product;
import com.example.inventoryservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        // Given
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Test product");
        product.setQuantity(10);
        product.setPrice(1000.50);
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList);

        // When
        List<Product> products = productService.getAllProducts();

        // Then
        assertEquals(1, products.size());
        assertEquals("Test product", products.get(0).getName());
    }

    @Test
    void getProductById() {
        // Given
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        // When
        Product product = productService.getProductById(2L);

        // Then
        assertNull(product);
    }

    @Test
    void saveProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}