package com.atlan.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CustomerDetails {
    @Id
    private int id;
    private String email;
    private String name;
    private double monthlyIncome;
    private double monthlySavings;
    private String mobileNumber;
}
