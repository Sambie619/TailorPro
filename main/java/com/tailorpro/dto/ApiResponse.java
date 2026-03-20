package com.tailorpro.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
}
