package com.tailorpro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tailorpro.entity.Order;
import com.tailorpro.entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByCustomerCustomerId(Long customerId);
    
    List<Order> findByStatusNot(OrderStatus status);
    
    long countByStatus(OrderStatus status);
    
    long countByDeliveryDate(LocalDate date);
}
