package com.tailorpro.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailorpro.dto.OrderDTO;
import com.tailorpro.entity.Customer;
import com.tailorpro.entity.Order;
import com.tailorpro.entity.OrderStatus;
import com.tailorpro.repository.CustomerRepository;
import com.tailorpro.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public OrderDTO createOrder(OrderDTO dto) {

	    Customer customer = customerRepository.findById(dto.getCustomerId())
	            .orElseThrow(() -> new RuntimeException("Customer not found"));

	    Order order = new Order();

	    order.setDressType(dto.getDressType());
	    order.setFabricProvided(dto.getFabricProvided());
	    order.setStitchingType(dto.getStitchingType());
	    order.setSpecialInstructions(dto.getSpecialInstructions());
	    order.setTrialDate(dto.getTrialDate());
	    order.setDeliveryDate(dto.getDeliveryDate());

	    order.setStatus(OrderStatus.PENDING); //  default

	    order.setCustomer(customer);

	    Order saved = orderRepository.save(order);

	    return new OrderDTO(
	            saved.getOrderId(),
	            saved.getDressType(),
	            saved.getFabricProvided(),
	            saved.getStitchingType(),
	            saved.getSpecialInstructions(),
	            saved.getTrialDate(),
	            saved.getDeliveryDate(),
	            saved.getStatus().name(),
	            customer.getCustomerId()
	    );
	}
	
	public OrderDTO updateStatus(Long orderId, String status) {

	    Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));

	    try {
	        OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
	        order.setStatus(newStatus);
	    } catch (IllegalArgumentException e) {
	        throw new RuntimeException("Invalid status value");
	    }

	    Order updated = orderRepository.save(order);

	    return new OrderDTO(
	            updated.getOrderId(),
	            updated.getDressType(),
	            updated.getFabricProvided(),
	            updated.getStitchingType(),
	            updated.getSpecialInstructions(),
	            updated.getTrialDate(),
	            updated.getDeliveryDate(),
	            updated.getStatus().name(),
	            updated.getCustomer().getCustomerId()
	    );
	}
	
	public List<OrderDTO> getOrdersByCustomer(Long customerId) {

	    return orderRepository.findByCustomerCustomerId(customerId)
	            .stream()
	            .map(order -> new OrderDTO(
	                    order.getOrderId(),
	                    order.getDressType(),
	                    order.getFabricProvided(),
	                    order.getStitchingType(),
	                    order.getSpecialInstructions(),
	                    order.getTrialDate(),
	                    order.getDeliveryDate(),
	                    order.getStatus().name(),
	                    order.getCustomer().getCustomerId()
	            ))
	            .toList();
	}
	
	public List<OrderDTO> getOrdersByStatus(String status) {

	    OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());

	    return orderRepository.findByStatus(orderStatus)
	            .stream()
	            .map(order -> new OrderDTO(
	                    order.getOrderId(),
	                    order.getDressType(),
	                    order.getFabricProvided(),
	                    order.getStitchingType(),
	                    order.getSpecialInstructions(),
	                    order.getTrialDate(),
	                    order.getDeliveryDate(),
	                    order.getStatus().name(),
	                    order.getCustomer().getCustomerId()
	            ))
	            .toList();
	}
	
	public OrderDTO markAsDelivered(Long orderId) {

	    Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));

	    order.setStatus(OrderStatus.DELIVERED);
	    order.setDeliveredDate(LocalDate.now());

	    Order updated = orderRepository.save(order);

	    return new OrderDTO(
	            updated.getOrderId(),
	            updated.getDressType(),
	            updated.getFabricProvided(),
	            updated.getStitchingType(),
	            updated.getSpecialInstructions(),
	            updated.getTrialDate(),
	            updated.getDeliveryDate(),
	            updated.getStatus().name(),
	            updated.getCustomer().getCustomerId()
	    );
	}
	
	public List<OrderDTO> getPendingDeliveries() {

	    return orderRepository.findByStatusNot(OrderStatus.DELIVERED)
	            .stream()
	            .map(order -> new OrderDTO(
	                    order.getOrderId(),
	                    order.getDressType(),
	                    order.getFabricProvided(),
	                    order.getStitchingType(),
	                    order.getSpecialInstructions(),
	                    order.getTrialDate(),
	                    order.getDeliveryDate(),
	                    order.getStatus().name(),
	                    order.getCustomer().getCustomerId()
	            ))
	            .toList();
	}
}
