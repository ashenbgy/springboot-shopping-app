package com.example.shoppingservice.controller;

import com.example.shoppingservice.dto.ProductDto;
import com.example.shoppingservice.exception.CustomException;
import com.example.shoppingservice.service.InventoryServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {
    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() throws CustomException {
        List<ProductDto> products = inventoryServiceClient.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
