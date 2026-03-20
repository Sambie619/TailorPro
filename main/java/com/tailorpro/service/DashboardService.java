package com.tailorpro.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailorpro.dto.DashboardDTO;
import com.tailorpro.entity.OrderStatus;
import com.tailorpro.repository.BillRepository;
import com.tailorpro.repository.CustomerRepository;
import com.tailorpro.repository.OrderRepository;

@Service
public class DashboardService {
	@Autowired
    private CustomerRepository customerRepository;
	@Autowired
    private OrderRepository orderRepository;
	@Autowired
    private BillRepository billRepository;
	public DashboardDTO getDashboard() {

	    long totalCustomers = customerRepository.count();

	    long activeOrders = orderRepository.countByStatus(OrderStatus.PENDING)
	            + orderRepository.countByStatus(OrderStatus.CUTTING)
	            + orderRepository.countByStatus(OrderStatus.STITCHING);

	    long completedOrders = orderRepository.countByStatus(OrderStatus.COMPLETED);

	    long dueToday = orderRepository.countByDeliveryDate(LocalDate.now());

	    Double revenue = billRepository.getTotalRevenue();
	    if (revenue == null) revenue = 0.0;

	    return new DashboardDTO(
	            totalCustomers,
	            activeOrders,
	            completedOrders,
	            dueToday,
	            revenue
	    );
	}
}
