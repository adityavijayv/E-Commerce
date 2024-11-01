package com.example.Order.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long employeeId;
    private Date orderDate;

    private Date requiredDate;
    private Date shippedDate;
    private String shipVia;
    private Double freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDetails_id")
    private OrderDetails orderDetails;

}
