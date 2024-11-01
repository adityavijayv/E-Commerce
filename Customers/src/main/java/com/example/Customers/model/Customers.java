package com.example.Customers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


        @Column(name = "CustomerID")
        private Integer Id;

        @Column(name = "CompanyName", nullable = false)
        private String companyName;

        @Column(name = "ContactName")
        private String contactName;

        @Column(name = "ContactTitle")
        private String contactTitle;

        @Column(name = "Address")
        private String address;

        @Column(name = "City")
        private String city;

        @Column(name = "Region")
        private String region;

        @Column(name = "PostalCode")
        private String postalCode;

        @Column(name = "Country")
        private String country;

        @Column(name = "Phone")
        private String phone;

        @Column(name = "Fax")
        private String fax;
}
