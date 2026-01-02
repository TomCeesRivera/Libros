package com.myapp.book.controller;

import com.myapp.book.dto.ErrorDTO;
import com.myapp.book.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDTO> bookNotFound(Exception ex){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Libro no válido!");
        errorDTO.setMessage(ex.getMessage());
        errorDTO.setDate(LocalDateTime.now());
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.putIfAbsent(err.getField(), err.getDefaultMessage()));

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Error de validación");
        errorDTO.setMessage("Uno o varios campos no son válidos");
        errorDTO.setDate(LocalDateTime.now());
        errorDTO.setErrors(errors);
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);

    }
}
