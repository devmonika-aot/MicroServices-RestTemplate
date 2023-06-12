package com.microservices.product.service;

import com.microservices.product.entity.Product;
import com.microservices.product.exception.ProductServiceException;
import com.microservices.product.model.ProductRequest;
import com.microservices.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductService {

  Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
  @Autowired ProductRepository productRepository;


  public long saveProduct(ProductRequest productRequest) {
    log.info("Additing product..");
    Product product =
        Product.builder()
            .productName(productRequest.getPname())
            .price(productRequest.getPrice())
            .quantity(productRequest.getQuantity())
            .build();
    if (product == null) {
      throw new ProductServiceException()
          .builder()
          .errorCode(501)
          .message("Something went wrong , Unable to save the record")
          .shortDescription("SERVER_ERROR")
          .build();
    }
    productRepository.save(product);
    log.info("Product Created");
    return product.getProductId();
  }

  @Override
  public Product getProductById(long id) {
    return productRepository
        .findById(id)
        .orElseThrow(
            () -> {
              throw new ProductServiceException()
                  .builder()
                  .errorCode(404)
                  .message("Product doesn't exist")
                  .shortDescription("NOT_FOUND")
                  .build();
            });
  }

  @Override
  public void reduceQuantity(long id, long quantity) {
    Product product = getProductById(id);
    product.setQuantity(product.getQuantity() - quantity);
    if (product.getQuantity() - quantity < 0) {
      throw new ProductServiceException()
          .builder()
          .errorCode(403)
          .message("Product does not have sufficient Quantity")
          .shortDescription("INSUFFICIENT_QUANTITY")
          .build();
    }
    productRepository.save(product);
  }
}
