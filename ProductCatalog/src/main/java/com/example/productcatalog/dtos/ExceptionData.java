package com.example.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionData {
    private HttpStatus status;
    private String message;

    public ExceptionData(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
