package com.atlan.backend.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SlangRequest {
    @NotBlank @NotNull
    private String fromLang;
    @NotBlank @NotNull
    private String toLang;
    @NotBlank @NotNull
    private String text;
}
