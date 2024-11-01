package com.example.Order.Controller;

import com.example.Order.DTO.OrderDTO;
import com.example.Order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("get/all")
    private List<OrderDTO> getall(){
        return orderService.getall();
    }

    @PostMapping("/save")
    private Long save (@RequestBody OrderDTO orderDTO){
        long saveOrder = orderService.saveOrder(orderDTO);
        return saveOrder;

    }

    @DeleteMapping("/delete/by/id/{id}")
    private void deleteorder(@PathVariable("id") Long id)
    {
        orderService.delete(id);
    }

    @GetMapping("/get/by/id/{id}")
    private OrderDTO getOrderById(@PathVariable("id") Long id) {
        return orderService.getorderbyid(id);
    }

    @PutMapping("/update/{id}")
    private int update(@PathVariable("id") Long id, @RequestBody OrderDTO orderDTO) {
        orderDTO.setId(id);
        return Math.toIntExact(orderService.update(orderDTO).getId());
    }

}
