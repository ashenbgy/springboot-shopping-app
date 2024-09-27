# Spring Boot Microservices Project

This project consists of two microservices built using Spring Boot: **Inventory Microservice** and **Shopping Microservice**. The Inventory Microservice provides a CRUD API for managing product details stored in a MySQL database, while the Shopping Microservice connects to the Inventory Microservice to display available products.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL

## Microservices Overview

### 1. Inventory Microservice
The Inventory Microservice manages product details and exposes a CRUD API for product management. The product details include:
- **Product Name**
- **Quantity**
- **Price**

**Endpoints:**
- `GET /api/products` - Retrieve all products
- `GET /api/products/{id}` - Retrieve a product by ID
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update an existing product
- `DELETE /api/products/{id}` - Delete a product

### 2. Shopping Microservice
The Shopping Microservice connects to the Inventory Microservice to fetch and display available products.

**Endpoints:**
- `GET /api/shopping/products` - Retrieve a list of available products from the Inventory Microservice.

## Database Setup
1. Set up a MySQL database.
2. Create a database called `inventory_db`.
