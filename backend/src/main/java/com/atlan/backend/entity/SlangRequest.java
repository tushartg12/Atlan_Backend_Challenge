package com.atlan.backend.entity;

import lombok.Data;

@Data
public class SlangRequest {
    private String fromLang;
    private String toLang;
    private String text;
}
