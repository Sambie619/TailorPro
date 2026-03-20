package com.tailorpro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tailorpro.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
	Optional<Bill> findByOrderOrderId(Long orderId);
	
	@Query("SELECT SUM(b.totalAmount) FROM Bill b")
	Double getTotalRevenue();
}
