package com.tailorpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tailorpro.dto.ApiResponse;
import com.tailorpro.dto.MeasurementDTO;
import com.tailorpro.service.MeasurementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @PostMapping
    public ApiResponse<MeasurementDTO> addMeasurement(@Valid @RequestBody MeasurementDTO dto) {
    	MeasurementDTO saved=measurementService.addMeasurement(dto);
    	return new ApiResponse<>("success", "Measurement added", saved);
    }
    
    @GetMapping("/customer/{customerId}")
    public List<MeasurementDTO> getByCustomer(@PathVariable("customerId") Long customerId) {
        return measurementService.getMeasurementsByCustomer(customerId);
    }
    
    @PutMapping("/{id}")
    public MeasurementDTO update(@PathVariable("id") Long id,
                                 @RequestBody MeasurementDTO dto) {
        return measurementService.updateMeasurement(id, dto);
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        measurementService.deleteMeasurement(id);
        return "Measurement deleted";
    }
}
