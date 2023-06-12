package com.microservices.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequest {

    private long quantity;
    private String pname;
    private long price;

}
