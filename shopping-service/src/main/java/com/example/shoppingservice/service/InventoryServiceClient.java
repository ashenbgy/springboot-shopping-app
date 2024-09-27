package com.example.shoppingservice.service;

import com.example.shoppingservice.dto.ProductDto;
import com.example.shoppingservice.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class InventoryServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Value("${inventory.service.products.endpoint}")
    private String inventoryServiceProducts;

    public List<ProductDto> getAllProducts() throws CustomException {
        String url = inventoryServiceUrl + inventoryServiceProducts;

        try {
            ProductDto[] products = restTemplate.getForObject(url, ProductDto[].class);

            if (products == null) {
                return Collections.emptyList();
            } else {
                return Arrays.asList(products);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
    }
}
