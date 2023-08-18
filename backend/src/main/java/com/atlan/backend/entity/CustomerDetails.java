package com.atlan.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;

@Data
public class CustomerDetails {
    @Id
    @Generated("uuid")
    private String id;
    private String email;
    private String name;
    private double monthlyIncome;
    private double monthlySavings;
    private String mobileNumber;
}
