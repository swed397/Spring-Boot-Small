package com.example.my.small.springboot.present.proj.dtos.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldValidationErrorDto {
    private String fieldName;
    private String errorMessage;
}
