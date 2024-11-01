package com.example.Customers.controller;

import com.example.Customers.dto.CustomersDto;
import com.example.Customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomersController {

    @Autowired
    CustomerService customerService;
    @GetMapping("/get/all")
    private List<CustomersDto> getAll(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/save")
    private int save(@RequestBody CustomersDto customersDto){
        int id = customerService.saveCustomers(customersDto);
        return id;
    }

    @GetMapping("/get/by/id/{Id}")
    private CustomersDto getCustomer(@PathVariable("Id") int Id) {
        return customerService.getCustomerById(Id);
    }

    @DeleteMapping("/delete/by/id/{Id}")
    private void delete(@PathVariable("Id") int Id) {
        customerService.delete(Id);
    }


    @PutMapping("/update/{Id}")
    private int update(@PathVariable("Id") int Id, @RequestBody CustomersDto customersDto) {
        customersDto.setId(Id);
        return customerService.update(customersDto).getId();
    }
}
