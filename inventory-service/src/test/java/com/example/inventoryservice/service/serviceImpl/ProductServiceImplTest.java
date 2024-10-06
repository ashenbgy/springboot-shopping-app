package com.example.inventoryservice.service.serviceImpl;

import com.example.inventoryservice.dto.ProductDto;
import com.example.inventoryservice.entity.Product;
import com.example.inventoryservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
        Product product = new Product();
        product.setId(2L);
        product.setName("Test Product");
        when(productRepository.findById(2L)).thenReturn(Optional.of(product));

        // When
        Product foundProduct = productService.getProductById(2L);

        // Then
        assertNotNull(foundProduct);
        assertEquals(2L, foundProduct.getId());
        assertEquals("Test Product", foundProduct.getName());
    }

    @Test
    void getProductByIdNotFound() {
        // Given
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        // When
        Product product = productService.getProductById(2L);

        // Then
        assertNull(product);
    }

    @Test
    void saveProduct() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setQuantity(5);
        productDto.setPrice(1500.50);

        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setQuantity(5);
        product.setPrice(1500.50);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        // When
        Product savedProduct = productService.saveProduct(productDto);

        // Then
        assertNotNull(savedProduct);
        assertEquals(1L, savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(5, savedProduct.getQuantity());
        assertEquals(1500.50, savedProduct.getPrice());
    }

    @Test
    void updateProduct() {
        // Given
        Product existingProduct = new Product();
        existingProduct.setId(2L);
        existingProduct.setName("Old Product");
        existingProduct.setQuantity(3);
        existingProduct.setPrice(500.00);

        ProductDto productDto = new ProductDto();
        productDto.setName("Updated Product");
        productDto.setQuantity(10);
        productDto.setPrice(200.50);

        when(productRepository.findById(2L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        // When
        Product updatedProduct = productService.updateProduct(2L, productDto);

        // Then
        assertNotNull(updatedProduct);
        assertEquals(2L, updatedProduct.getId());
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals(10, updatedProduct.getQuantity());
        assertEquals(200.50, updatedProduct.getPrice());
    }

    @Test
    void updateProductNotFound() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setName("Updated Product");
        productDto.setQuantity(10);
        productDto.setPrice(200.50);

        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(2L, productDto));
    }

    @Test
    void deleteProduct() {
        // When
        productService.deleteProduct(1L);

        // Then
        verify(productRepository).deleteById(1L);
    }

    @Test
    void deleteProductNotFound() {
        // Given
        Long productId = 1L;

        doThrow(new EntityNotFoundException("Product not found")).when(productRepository).deleteById(productId);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> productService.deleteProduct(productId));
    }
}