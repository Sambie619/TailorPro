package com.tailorpro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "measurements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId;

    private Double chest;
    private Double waist;
    private Double shoulder;
    private Double sleeveLength;
    private Double neck;
    private Double hip;
    private Double length;

    // RELATION
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
