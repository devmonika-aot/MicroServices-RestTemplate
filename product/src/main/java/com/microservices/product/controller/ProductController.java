package com.microservices.product.controller;

import com.microservices.product.entity.Product;
import com.microservices.product.exception.ProductServiceException;
import com.microservices.product.model.ProductRequest;
import com.microservices.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired ProductService productService;

  @PostMapping("/saveProduct")
  public long addProduct(@RequestBody ProductRequest productRequest) {
    return  productService.saveProduct(productRequest);
  }

  @GetMapping("/{id}")
  public Product geProductById(@PathVariable("id") long id) {
    System.out.println("productId" + id);
    return productService.getProductById(id);
  }

  @PutMapping("/reduceQuantity/{id}")
  public void reduceQuantity(@PathVariable("id") long id, @RequestParam long quantity) {

    productService.reduceQuantity(id, quantity);
  }
}
