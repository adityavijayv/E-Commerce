package com.northwind.Employees.controller;

import com.northwind.Employees.dto.EmployeeDTO;
import com.northwind.Employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("get/all")
    private List<EmployeeDTO> getAll(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/save")
    private Integer save (@RequestBody EmployeeDTO employeeDTO){
        Integer saveEmployees = employeeService.saveEmployee(employeeDTO);
        return saveEmployees;
    }

    @DeleteMapping("/delete/by/id/{id}")
    private void deleteEmployee(@PathVariable("id") Integer id){
        employeeService.delete(id);
    }

    @GetMapping("/getEmployee/by/id/{id}")
    private EmployeeDTO getEmployeeById(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/updateEmployee/{id}")
    private int updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        return (employeeService.updateEmployees(employeeDTO).getId());
    }
}
