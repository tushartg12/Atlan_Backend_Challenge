package com.atlan.backend.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;

@Data
public class CustomerDetails {
    @Id @Generated("uuid")
    private String id;
    @NotNull @NotBlank
    private String email;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank @PositiveOrZero
    private double monthlyIncome;
    @NotNull @NotBlank @PositiveOrZero
    private double monthlySavings;
    @NotNull @NotBlank
    private String mobileNumber;
}
