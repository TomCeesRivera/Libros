package com.myapp.book.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
public class ErrorDTO {

    private String error;

    private String message;

    private Integer status;

    private LocalDateTime date;

    private Map<String, String> errors;
}
