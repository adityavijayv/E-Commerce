package com.example.Product.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private  String productName;
    private  Integer unitPrice;
    private Integer unitInStock;
    private Integer unitsOnOrder;
    private Integer categoryId;
    private String categoryName;
}
