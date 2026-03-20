package com.tailorpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tailorpro.dto.ApiResponse;
import com.tailorpro.dto.OrderDTO;
import com.tailorpro.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO dto) {
        return orderService.createOrder(dto);
    }

    @PutMapping("/{id}/status")
    public OrderDTO updateStatus(@PathVariable Long id,
                                 @RequestParam String status) {
        return orderService.updateStatus(id, status);
    }
    
    @GetMapping("/customer/{customerId}")
    public List<OrderDTO> getByCustomer(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomer(customerId);
    }
    
    @GetMapping("/status")
    public ApiResponse<List<OrderDTO>> getByStatus(@RequestParam String status) {
        return new ApiResponse<>(
                "success",
                "Orders fetched",
                orderService.getOrdersByStatus(status)
        );
    }
    
    @PutMapping("/{id}/deliver")
    public OrderDTO deliver(@PathVariable Long id) {
        return orderService.markAsDelivered(id);
    }
    
    @GetMapping("/pending-deliveries")
    public List<OrderDTO> getPendingDeliveries() {
        return orderService.getPendingDeliveries();
    }
}