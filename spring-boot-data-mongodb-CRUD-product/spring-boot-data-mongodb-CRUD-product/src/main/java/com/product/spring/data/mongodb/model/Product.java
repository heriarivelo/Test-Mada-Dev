package com.product.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {
  @Id
  private String id;

  private String name;
  private Integer price;
  private String description;
  

  public Product() {

  }

  public Product(String name, Integer price,String description) {
    this.name = name;
    this.price = price;
    this.description = description;
    
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(Integer price){
    this.price = price;
  }

  public Integer getPrice(){
    return price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ",  price=" + price +",desc=" + description + "]";
  }
}
