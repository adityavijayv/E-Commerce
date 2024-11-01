package com.northwind.Employees.service;

import com.northwind.Employees.dto.EmployeeDTO;
import com.northwind.Employees.model.Employees;
import com.northwind.Employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<Employees> employeesList = employeeRepository.findAll();
        for (Employees employees : employeesList) {
            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setId(employees.getId());
            employeeDTO.setLastName(employees.getLastName());
            employeeDTO.setFirstName(employees.getLastName());
            employeeDTO.setTitle(employees.getTitle());
            employeeDTO.setTitleOfCourtesy(employees.getTitleOfCourtesy());
            employeeDTO.setBirthDate(employees.getBirthDate());
            employeeDTO.setHireDate(employees.getHireDate());
            employeeDTO.setAddress(employees.getAddress());
            employeeDTO.setCity(employees.getCity());
            employeeDTO.setRegion(employees.getRegion());
            employeeDTO.setPostalCode(employees.getPostalCode());
            employeeDTO.setCountry(employees.getCountry());
            employeeDTO.setHomePhone(employees.getHomePhone());
            employeeDTO.setExtension(employees.getExtension());
            employeeDTO.setNotes(employees.getNotes());
            employeeDTO.setReportsTo(employees.getReportsTo());

            employeeDTOList.add(employeeDTO);

        }
        return employeeDTOList;
    }

    public int saveEmployee(EmployeeDTO employeeDTO){
        EmployeeDTO dto = new EmployeeDTO();
        Employees employees = new Employees();

        employees.setId(employeeDTO.getId());
        employees.setLastName(employeeDTO.getLastName());
        employees.setFirstName(employeeDTO.getLastName());
        employees.setTitle(employeeDTO.getTitle());
        employees.setTitleOfCourtesy(employeeDTO.getTitleOfCourtesy());
        employees.setBirthDate(employeeDTO.getBirthDate());
        employees.setHireDate(employeeDTO.getHireDate());
        employees.setAddress(employeeDTO.getAddress());
        employees.setCity(employeeDTO.getCity());
        employees.setRegion(employeeDTO.getRegion());
        employees.setPostalCode(employeeDTO.getPostalCode());
        employees.setCountry(employeeDTO.getCountry());
        employees.setHomePhone(employeeDTO.getHomePhone());
        employees.setExtension(employeeDTO.getExtension());
        employees.setNotes(employeeDTO.getNotes());
        employees.setReportsTo(employeeDTO.getReportsTo());

        Employees save = employeeRepository.save(employees);

        return save.getId();
    }

    public EmployeeDTO getEmployeeById(Integer Id){
        Optional <Employees> employees =  employeeRepository.findById(Id);
        EmployeeDTO employeeDTO = new EmployeeDTO();

        if(!employees.isPresent()){
            throw new RuntimeException("Given Id is not Present");
        }
        else {
            employeeDTO.setId(employees.get().getId());
            employeeDTO.setLastName(employees.get().getLastName());
            employeeDTO.setFirstName(employees.get().getLastName());
            employeeDTO.setTitle(employees.get().getTitle());
            employeeDTO.setTitleOfCourtesy(employees.get().getTitleOfCourtesy());
            employeeDTO.setBirthDate(employees.get().getBirthDate());
            employeeDTO.setHireDate(employees.get().getHireDate());
            employeeDTO.setAddress(employees.get().getAddress());
            employeeDTO.setCity(employees.get().getCity());
            employeeDTO.setRegion(employees.get().getRegion());
            employeeDTO.setPostalCode(employees.get().getPostalCode());
            employeeDTO.setCountry(employees.get().getCountry());
            employeeDTO.setHomePhone(employees.get().getHomePhone());
            employeeDTO.setExtension(employees.get().getExtension());
            employeeDTO.setNotes(employees.get().getNotes());
            employeeDTO.setReportsTo(employees.get().getReportsTo());
        }
        return employeeDTO;
    }

    public EmployeeDTO updateEmployees (EmployeeDTO employeeDTO){
        Optional <Employees> optionalEmployees = employeeRepository.findById(employeeDTO.getId());
        EmployeeDTO dto = new EmployeeDTO();
        Employees employees = new Employees();
        if (optionalEmployees.isPresent()){

            employees.setId(employeeDTO.getId());
            employees.setLastName(employeeDTO.getLastName());
            employees.setFirstName(employeeDTO.getLastName());
            employees.setTitle(employeeDTO.getTitle());
            employees.setTitleOfCourtesy(employeeDTO.getTitleOfCourtesy());
            employees.setBirthDate(employeeDTO.getBirthDate());
            employees.setHireDate(employeeDTO.getHireDate());
            employees.setAddress(employeeDTO.getAddress());
            employees.setCity(employeeDTO.getCity());
            employees.setRegion(employeeDTO.getRegion());
            employees.setPostalCode(employeeDTO.getPostalCode());
            employees.setCountry(employeeDTO.getCountry());
            employees.setHomePhone(employeeDTO.getHomePhone());
            employees.setExtension(employeeDTO.getExtension());
            employees.setNotes(employeeDTO.getNotes());
            employees.setReportsTo(employeeDTO.getReportsTo());

            Employees save = employeeRepository.save(employees);
            dto.setId(save.getId());
        }
        else {
            throw new RuntimeException("Given Id is not Present");
        }
        return dto;
    }

    public void delete (Integer Id){
        Optional <Employees> employees = employeeRepository.findById(Id);
        if(employees.isPresent()){
            employeeRepository.deleteById(Id);
        }
        else {
            throw new RuntimeException("Given Id is not Present");
        }
    }
}

