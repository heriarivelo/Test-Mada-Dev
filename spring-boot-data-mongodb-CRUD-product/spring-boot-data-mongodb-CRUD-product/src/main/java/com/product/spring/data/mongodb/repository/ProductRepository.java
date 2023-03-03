package com.product.spring.data.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.product.spring.data.mongodb.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
  
   List<Product> findByNameProduct(String name); //Filtre du produits
}
