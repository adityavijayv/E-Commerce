package com.northwind.Employees.repository;

import com.northwind.Employees.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <Employees , Integer> {
}
