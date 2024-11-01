package com.example.Order.DTO;

import lombok.Data;

@Data
public class OrderDetailsDTO {
    private Long id;

    private Long productId;
    private Double unitPrice;
    private Integer quantity;
    private Double discount;
}
