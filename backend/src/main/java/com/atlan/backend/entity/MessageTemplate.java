package com.atlan.backend.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageTemplate {
    @NotBlank @NotNull
    private String mobileNumber;
    @NotBlank @NotBlank
    private String messgeBody;
}
