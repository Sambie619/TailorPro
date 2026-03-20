package com.tailorpro.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long orderId;
    @NotBlank
    private String dressType;
    @NotNull
    private Boolean fabricProvided;
    private String stitchingType;
    private String specialInstructions;

    private LocalDate trialDate;
    private LocalDate deliveryDate;

    private String status;
    @NotNull
    private Long customerId;
}
