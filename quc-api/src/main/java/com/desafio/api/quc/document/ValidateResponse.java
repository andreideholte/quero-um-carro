package com.desafio.api.quc.document;

import lombok.Data;

@Data
public class ValidateResponse {

    private Boolean isValidEmail;
    private Boolean isUpToDate;
    
}