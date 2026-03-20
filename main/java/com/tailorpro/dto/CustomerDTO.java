package com.tailorpro.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long customerId;
    private String name;
    private String phoneNumber;
    private String address;
    private String gender;
    private String email;
}
