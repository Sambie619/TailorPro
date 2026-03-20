package com.tailorpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailorpro.dto.MeasurementDTO;
import com.tailorpro.entity.Customer;
import com.tailorpro.entity.Measurement;
import com.tailorpro.repository.CustomerRepository;
import com.tailorpro.repository.MeasurementRepository;

@Service
public class MeasurementService {

	@Autowired
	private MeasurementRepository measurementRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public MeasurementDTO addMeasurement(MeasurementDTO dto) {

	    Customer customer = customerRepository.findById(dto.getCustomerId())
	            .orElseThrow(() -> new RuntimeException("Customer not found"));

	    Measurement measurement = new Measurement();

	    measurement.setChest(dto.getChest());
	    measurement.setWaist(dto.getWaist());
	    measurement.setShoulder(dto.getShoulder());
	    measurement.setSleeveLength(dto.getSleeveLength());
	    measurement.setNeck(dto.getNeck());
	    measurement.setHip(dto.getHip());
	    measurement.setLength(dto.getLength());
	    measurement.setCustomer(customer);

	    Measurement saved = measurementRepository.save(measurement);

	    return new MeasurementDTO(
	            saved.getMeasurementId(),
	            saved.getChest(),
	            saved.getWaist(),
	            saved.getShoulder(),
	            saved.getSleeveLength(),
	            saved.getNeck(),
	            saved.getHip(),
	            saved.getLength(),
	            customer.getCustomerId()
	    );
	}
	
	public List<MeasurementDTO> getMeasurementsByCustomer(Long customerId) {

	    return measurementRepository
	            .findByCustomerCustomerId(customerId)
	            .stream()
	            .map(m -> new MeasurementDTO(
	                    m.getMeasurementId(),
	                    m.getChest(),
	                    m.getWaist(),
	                    m.getShoulder(),
	                    m.getSleeveLength(),
	                    m.getNeck(),
	                    m.getHip(),
	                    m.getLength(),
	                    m.getCustomer().getCustomerId()
	            ))
	            .toList();
	}
	
	public MeasurementDTO updateMeasurement(Long id, MeasurementDTO dto) {

	    Measurement m = measurementRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Measurement not found"));

	    m.setChest(dto.getChest());
	    m.setWaist(dto.getWaist());
	    m.setShoulder(dto.getShoulder());
	    m.setSleeveLength(dto.getSleeveLength());
	    m.setNeck(dto.getNeck());
	    m.setHip(dto.getHip());
	    m.setLength(dto.getLength());

	    Measurement updated = measurementRepository.save(m);

	    return new MeasurementDTO(
	            updated.getMeasurementId(),
	            updated.getChest(),
	            updated.getWaist(),
	            updated.getShoulder(),
	            updated.getSleeveLength(),
	            updated.getNeck(),
	            updated.getHip(),
	            updated.getLength(),
	            updated.getCustomer().getCustomerId()
	    );
	}
	
	public void deleteMeasurement(Long id) {
	    measurementRepository.deleteById(id);
	}
}
