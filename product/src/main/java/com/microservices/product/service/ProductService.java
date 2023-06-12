package com.microservices.product.service;

import com.microservices.product.entity.Product;
import com.microservices.product.model.ProductRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface ProductService {
    public long saveProduct(ProductRequest productRequest);
    public Product getProductById(long id);

    void reduceQuantity(long id, long quantity);
}
