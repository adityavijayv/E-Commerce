package com.example.Order.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
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
        private Long productId;
        private Double unitPrice;
        private Integer quantity;
        private Double discount;

    }
