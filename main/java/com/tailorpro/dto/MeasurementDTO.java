package com.tailorpro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {

    private Long measurementId;
    @NotNull
    private Double chest;
    @NotNull
    private Double waist;
    @NotNull
    private Double shoulder;
    @NotNull
    private Double sleeveLength;
    @NotNull
    private Double neck;
    @NotNull
    private Double hip;
    @NotNull
    private Double length;
    @NotNull
    private Long customerId; //  important
}
