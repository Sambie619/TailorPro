package com.tailorpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tailorpro.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
	public List<Measurement> findByCustomerCustomerId(Long customerId);
}
