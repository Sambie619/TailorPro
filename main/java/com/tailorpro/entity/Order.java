package com.tailorpro.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String dressType;
    private Boolean fabricProvided;
    private String stitchingType;
    private String specialInstructions;

    private LocalDate trialDate;
    private LocalDate deliveryDate;
    private LocalDate deliveredDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //  RELATION
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
