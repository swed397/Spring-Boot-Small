package com.example.my.small.springboot.present.proj.mappers;

import com.example.my.small.springboot.present.proj.dtos.movies.MovieCreateDto;
import com.example.my.small.springboot.present.proj.dtos.movies.MovieDto;
import com.example.my.small.springboot.present.proj.entities.Movie;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-20T02:19:47+0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public MovieDto toDto(Movie source) {
        if ( source == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setId( source.getId() );
        movieDto.setTitle( source.getTitle() );
        movieDto.setYear( source.getYear() );

        return movieDto;
    }

    @Override
    public Movie toEntity(MovieCreateDto source) {
        if ( source == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setTitle( source.getTitle() );
        movie.setYear( source.getYear() );

        return movie;
    }
}
