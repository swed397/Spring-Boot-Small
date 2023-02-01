package com.example.my.small.springboot.present.proj.configs.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class InternalServerError extends Throwable {

    @ExceptionHandler()
    private ResponseEntity handleIoException(IOException e) {
        return ResponseEntity.internalServerError().build();
    }
}
