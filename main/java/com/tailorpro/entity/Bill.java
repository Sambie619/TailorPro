package com.tailorpro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    private Double totalAmount;
    private Double advancePaid;
    private Double balanceAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    //  RELATION
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
