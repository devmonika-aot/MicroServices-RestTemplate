package com.microservices.order.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.order.model.OrderRequest;
import com.microservices.order.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest) ;

    OrderResponse getOrderDetails(long orderId);
}
