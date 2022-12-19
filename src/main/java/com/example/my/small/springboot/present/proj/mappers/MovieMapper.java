package com.example.my.small.springboot.present.proj.mappers;

import com.example.my.small.springboot.present.proj.entities.Movie;
import com.example.my.small.springboot.present.proj.dtos.movies.MovieCreateDto;
import com.example.my.small.springboot.present.proj.dtos.movies.MovieDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto toDto(Movie source);
    Movie toEntity(MovieCreateDto source);
}
