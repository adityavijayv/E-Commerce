package com.example.Order.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Double unitPrice;
    private Integer quantity;
    private Double discount;

    @OneToOne(mappedBy = "orderDetails")
    private Order order;


}

