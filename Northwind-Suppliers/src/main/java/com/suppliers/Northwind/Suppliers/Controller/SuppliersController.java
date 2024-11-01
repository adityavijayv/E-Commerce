package com.suppliers.Northwind.Suppliers.Controller;

import com.suppliers.Northwind.Suppliers.DTO.SuppliersDTO;
import com.suppliers.Northwind.Suppliers.Service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuppliersController {
    @Autowired
    SuppliersService supplierService;
    @GetMapping("/get/all")
    private List<SuppliersDTO> getAll(){
        return supplierService.getAllSuppliers();
    }

    @PostMapping("/save")
    private int save(@RequestBody SuppliersDTO suppliersDTO){
        int id = supplierService.saveSuppliers(suppliersDTO);
        return id;
    }

    @GetMapping("/get/by/id/{supplierid}")
    private SuppliersDTO getSupplier(@PathVariable("supplierid") int supplierid) {
        return supplierService.getSupplierbyId(supplierid);
    }

    @DeleteMapping("/delete/by/id/{supplierid}")
    private void deleteSupplier(@PathVariable("supplierid") int supplierid) {
        supplierService.delete(supplierid);
    }


    @PutMapping("/update/{supplierid}")
    private int update(@PathVariable("supplierid") int supplierid, @RequestBody SuppliersDTO suppliersDto) {
        suppliersDto.setSupplierid(supplierid);
        return supplierService.update(suppliersDto).getSupplierid();
    }
}