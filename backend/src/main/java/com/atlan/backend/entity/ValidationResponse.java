package com.atlan.backend.entity;

import lombok.Data;

@Data
public class ValidationResponse {
    private boolean emailValidity;
    private boolean incomeValidity;
    private boolean mobileNumberValidity;
}
