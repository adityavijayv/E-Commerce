package com.example.Customers.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomersDto {
    private Integer Id;
    private String companyName;

    private String contactName;

    private String contactTitle;

    private String address;

    private String city;

    private String region;

    private String postalCode;

    private String country;

    private String phone;

    private String fax;
}
