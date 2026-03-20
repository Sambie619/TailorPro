package com.tailorpro.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {

    private Long totalCustomers;
    private Long activeOrders;
    private Long completedOrders;
    private Long ordersDueToday;
    private Double totalRevenue;
}
