package com.northwind.Employees.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {
    private Integer id;
    private String lastName;
    private String firstName;
    private String title;
    private String titleOfCourtesy;
    private Date birthDate;
    private Date hireDate;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private String extension;
    private String notes;
    private String reportsTo;
}
