package com.tailorpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tailorpro.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	long count();
}
