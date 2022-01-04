package com.example.serviceorder.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDto {
    private String orderPassword;
    private long clientId;
    private long eventId;
    private LocalDate orderDate;
}
