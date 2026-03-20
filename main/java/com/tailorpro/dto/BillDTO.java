package com.tailorpro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {

    private Long billId;
    @NotNull
    @Positive
    private Double totalAmount;
    @NotNull
    @PositiveOrZero
    private Double advancePaid;
    private Double balanceAmount;

    private String paymentStatus;
    @NotNull
    private Long orderId;
}
