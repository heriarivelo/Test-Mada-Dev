package com.product.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.spring.data.mongodb.model.Product;
import com.product.spring.data.mongodb.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  /*get all products in database  */
  @GetMapping("/products")
  public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
    try {
      List<Product> products = new ArrayList<Product>();

      if (name == null)
        productRepository.findAll().forEach(products::add);
      else
        productRepository.findByNameProduct(name).forEach(products::add);

      if (products.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /* get a product by Id in database */
  @GetMapping("/products/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
    Optional<Product> productData = productRepository.findById(id);

    if (productData.isPresent()) {
      return new ResponseEntity<>(productData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /* create a new product in database */  
  @PostMapping("/products")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    try {
      Product _product = productRepository.save(new Product(product.getName(), product.getPrice(), product.getDescription()));
      return new ResponseEntity<>(_product, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /* Update product : name , price , description  in database*/
  @PutMapping("/product/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
    Optional<Product> productData = productRepository.findById(id);

    if (productData.isPresent()) {
      Product _product = productData.get();
      _product.setName(product.getName());
      _product.setDescription(product.getDescription());
      _product.setPrice(product.getPrice());
      return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /* Delete product by Id in database*/
  @DeleteMapping("/product/{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
    try {
      productRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /* Delete All Products  */
  @DeleteMapping("/product")
  public ResponseEntity<HttpStatus> deleteAllProducts() {
    try {
      productRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
  

}
