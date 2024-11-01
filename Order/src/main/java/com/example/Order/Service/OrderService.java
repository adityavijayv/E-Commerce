package com.example.Order.Service;

import com.example.Order.DTO.OrderDTO;
import com.example.Order.DTO.OrderDetailsDTO;
import com.example.Order.model.Order;
import com.example.Order.model.OrderDetails;
import com.example.Order.repository.OrderDetailsRepository;
import com.example.Order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public long saveOrder(OrderDTO orderDTO){
        OrderDTO orderDTO1 = new OrderDTO();
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        Order order = new Order();


        order.setId(orderDTO.getId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setEmployeeId(orderDTO.getEmployeeId());
        order.setRequiredDate(orderDTO.getRequiredDate());
        order.setShippedDate(orderDTO.getShippedDate());
        order.setShipVia(orderDTO.getShipVia());
        order.setFreight(orderDTO.getFreight());
        order.setShipName(orderDTO.getShipName());
        order.setShipAddress(orderDTO.getShipAddress());
        order.setShipCity(orderDTO.getShipCity());
        order.setShipRegion(orderDTO.getShipRegion());
        order.setShipPostalCode(orderDTO.getShipPostalCode());
        order.setShipCountry(orderDTO.getShipCountry());

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrder(order);

        orderDetails.setProductId(orderDTO.getProductId());
        orderDetails.setUnitPrice(orderDTO.getUnitPrice());
        orderDetails.setQuantity(orderDTO.getQuantity());
        orderDetails.setDiscount(orderDetailsDTO.getDiscount());
        order.setOrderDetails(orderDetails);
        Order save= orderRepository.save(order);

        if(save != null){
            updateProductStock(Math.toIntExact(orderDTO.getProductId()),orderDTO.getQuantity());
        }

        return save.getId();
    }

    public List<OrderDTO> getall(){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(Order order : orderRepository.findAll()){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setFreight(order.getFreight());
            orderDTO.setShipCity(order.getShipCity());
            orderDTO.setShipAddress(order.getShipAddress());
            orderDTO.setRequiredDate(order.getRequiredDate());
            orderDTO.setShippedDate(order.getShippedDate());
            orderDTO.setShipCountry(order.getShipCountry());
            orderDTO.setShipVia(order.getShipVia());
            orderDTO.setShipName(order.getShipName());
            orderDTO.setShipRegion(order.getShipRegion());
            orderDTO.setShipPostalCode(order.getShipPostalCode());

            if(order.getOrderDetails() != null){
                orderDTO.setUnitPrice(order.getOrderDetails().getUnitPrice());
                orderDTO.setQuantity(order.getOrderDetails().getQuantity());
                orderDTO.setProductId(order.getOrderDetails().getProductId());
            }
            orderDTOList.add(orderDTO);
        }

        return orderDTOList;
    }

    public OrderDTO update(OrderDTO orderDTO){
        Optional<Order> optionalOrder = orderRepository.findById(orderDTO.getId());
        OrderDTO dto = new OrderDTO();
         Order order = new Order();
         if(optionalOrder.isPresent()){
             order.setId(orderDTO.getId());
             order.setOrderDate(orderDTO.getOrderDate());
             order.setCustomerId(orderDTO.getCustomerId());
             order.setEmployeeId(orderDTO.getEmployeeId());
             order.setRequiredDate(orderDTO.getRequiredDate());
             order.setShippedDate(orderDTO.getShippedDate());
             order.setShipVia(orderDTO.getShipVia());
             order.setFreight(orderDTO.getFreight());
             order.setShipName(orderDTO.getShipName());
             order.setShipAddress(orderDTO.getShipAddress());
             order.setShipCity(orderDTO.getShipCity());
             order.setShipRegion(orderDTO.getShipRegion());
             order.setShipPostalCode(orderDTO.getShipPostalCode());
             order.setShipCountry(orderDTO.getShipCountry());

             Order save = orderRepository.save(order);
             dto.setId(save.getId());
         }
         else
         {
             throw new RuntimeException("Given ID Not Found");
         }
         return dto;

    }


    public OrderDTO getorderbyid(Long Id){
        Optional <Order> order = orderRepository.findById(Id);
        OrderDTO orderDTO = new OrderDTO();

        if(!order.isPresent()){
            throw new RuntimeException("Given Id is not Present");
        }
        orderDTO.setId(order.get().getId());
        orderDTO.setOrderDate(order.get().getOrderDate());
        orderDTO.setFreight(order.get().getFreight());
        orderDTO.setShipCity(order.get().getShipCity());
        orderDTO.setShipAddress(order.get().getShipAddress());
        orderDTO.setRequiredDate(order.get().getRequiredDate());
        orderDTO.setShippedDate(order.get().getShippedDate());
        orderDTO.setShipCountry(order.get().getShipCountry());
        orderDTO.setShipVia(order.get().getShipVia());
        orderDTO.setShipName(order.get().getShipName());
        orderDTO.setShipRegion(order.get().getShipRegion());
        orderDTO.setShipPostalCode(order.get().getShipPostalCode());

        if(order.get().getOrderDetails() != null){
            orderDTO.setUnitPrice(order.get().getOrderDetails().getUnitPrice());
            orderDTO.setQuantity(order.get().getOrderDetails().getQuantity());
            orderDTO.setProductId(order.get().getOrderDetails().getProductId());
        }
        return orderDTO;
    }

    public void delete(Long id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.deleteById(id);
        } else{
            throw new RuntimeException("Given ID is not present");
        }
    }

    private void updateProductStock(Integer ProductId , Integer Unit){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        Map <String,Integer> map = new HashMap<>();
        map.put("ProductId",ProductId);
        map.put("Unit",Unit);

        HttpEntity <Map<String,Integer>> entity = new HttpEntity<>(map,httpHeaders);
        String url = "http://localhost:8082/updateProduct";

        ResponseEntity <Void> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,entity, Void.class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            System.out.println("Product stock updated");
        }
        else {
            throw new RuntimeException("failed");
        }

    }
}