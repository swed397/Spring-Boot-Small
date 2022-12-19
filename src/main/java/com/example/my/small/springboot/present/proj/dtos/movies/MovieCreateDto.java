package com.example.my.small.springboot.present.proj.dtos.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreateDto {

    @NotBlank(message = "Title is NOT NULL constraint")
    private String title;

    @Min(value = 1900, message = "Year must be > 1900")
    private Integer year;
}
