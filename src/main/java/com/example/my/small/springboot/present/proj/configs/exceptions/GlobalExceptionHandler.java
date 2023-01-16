package com.example.my.small.springboot.present.proj.configs.exceptions;

import com.example.my.small.springboot.present.proj.dtos.errors.FieldValidationErrorDto;
import com.example.my.small.springboot.present.proj.dtos.errors.RequestValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleNotArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldValidationErrorDto> errorDtos = exception.getFieldErrors().stream()
                .map(fieldError -> new FieldValidationErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RequestValidationErrors(errorDtos));
    }
}
