package com.example.Customers.service;

import com.example.Customers.dto.CustomersDto;
import com.example.Customers.model.Customers;
import com.example.Customers.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomersRepository customersRepository;
    public List<CustomersDto> getAllCustomers(){

        List<CustomersDto> customersDtos = new ArrayList<>();
        List<Customers> customersList = customersRepository.findAll();

        for(int i = 0; i < customersList.size(); i++) {
            Customers customers = customersList.get(i);

            CustomersDto customersDto = new CustomersDto();

            customersDto.setId(customers.getId());
            customersDto.setCompanyName(customers.getCompanyName());
            customersDto.setContactName(customers.getContactName());
            customersDto.setContactTitle(customers.getContactTitle());
            customersDto.setAddress(customers.getAddress());
            customersDto.setCity(customers.getCity());
            customersDto.setRegion(customers.getRegion());
            customersDto.setPostalCode(customers.getPostalCode());
            customersDto.setCountry(customers.getCountry());
            customersDto.setPhone(customers.getPhone());
            customersDto.setFax(customers.getFax());


            customersDtos.add(customersDto);

        }
        return customersDtos;
    }




    public int saveCustomers(CustomersDto customersDto){

        CustomersDto customersDto1 = new CustomersDto();
        Customers customers = new Customers();

        customers.setId(customersDto1.getId());
        customers.setCompanyName(customersDto1.getCompanyName());
        customers.setContactName(customersDto1.getContactName());
        customers.setContactTitle(customersDto1.getContactTitle());
        customers.setAddress(customersDto1.getAddress());
        customers.setCity(customersDto1.getCity());
        customers.setRegion(customersDto1.getRegion());
        customers.setPostalCode(customersDto1.getPostalCode());
        customers.setCountry(customersDto1.getCountry());
        customers.setPhone(customersDto1.getPhone());
        customers.setFax(customersDto1.getFax());



        Customers save = customersRepository.save(customers);
        customersDto.setId(save.getId());

        return customersDto.getId();
    }




    public CustomersDto getCustomerById(Integer Id){
        Optional<Customers> customers =customersRepository.findById(Id);

        CustomersDto customersDto = new CustomersDto();

        if (!customers.isPresent()){
            throw new RuntimeException("Given Id not Present");
        }
        customersDto.setId(customers.get().getId());
        customersDto.setCompanyName(customers.get().getCompanyName());
        customersDto.setContactName(customers.get().getContactName());
        customersDto.setContactTitle(customers.get().getContactTitle());
        customersDto.setAddress(customers.get().getAddress());
        customersDto.setCity(customers.get().getCity());
        customersDto.setRegion(customers.get().getRegion());
        customersDto.setPostalCode(customers.get().getPostalCode());
        customersDto.setCountry(customers.get().getCountry());
        customersDto.setPhone(customers.get().getPhone());
        customersDto.setFax(customers.get().getFax());

        return customersDto ;
    }


    public CustomersDto update(CustomersDto customersDto) {
        Optional<Customers> optionalCustomers = customersRepository.findById(customersDto.getId());
        CustomersDto dto =new CustomersDto();
       Customers customers = new Customers();
        if (optionalCustomers.isPresent()) {

            customers.setId(customersDto.getId());
            customers.setCompanyName(customersDto.getCompanyName());
            customers.setContactName(customersDto.getContactName());
            customers.setContactTitle(customersDto.getContactTitle());
            customers.setAddress(customersDto.getAddress());
            customers.setCity(customersDto.getCity());
            customers.setRegion(customersDto.getRegion());
            customers.setPostalCode(customersDto.getPostalCode());
            customers.setCountry(customersDto.getCountry());
            customers.setPhone(customersDto.getPhone());
            customers.setFax(customersDto.getFax());

            Customers saveCustomers=customersRepository.save(customers);
            dto.setId(saveCustomers.getId());

        } else {
            throw new RuntimeException("supplier Not Present");
        }
        return dto;
    }

    public void delete(Integer id) {
        Optional<Customers> customers = customersRepository.findById(id);
        if (customers.isPresent()) {
            customersRepository.deleteById(id);

        } else {
            throw new RuntimeException("Given ID not present");
        }


    }
}
