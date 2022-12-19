package com.example.my.small.springboot.present.proj.controllers;

import com.example.my.small.springboot.present.proj.entities.Movie;
import com.example.my.small.springboot.present.proj.mappers.MovieMapper;
import com.example.my.small.springboot.present.proj.services.MovieService;
import com.example.my.small.springboot.present.proj.dtos.movies.MovieCreateDto;
import com.example.my.small.springboot.present.proj.dtos.movies.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {

    public final MovieService service;
    public final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService service, MovieMapper movieMapper) {
        this.service = service;
        this.movieMapper = movieMapper;
    }

    @GetMapping("")
    public ResponseEntity<List> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(service.get(id).map(movieMapper::toDto));
    }

    @PostMapping("")
    public ResponseEntity<MovieDto> create(@RequestBody @Valid MovieCreateDto movieCreateDto) {
        Movie movie = service.create(movieMapper.toEntity(movieCreateDto));
        return ResponseEntity.ok(movieMapper.toDto(movie));
    }
}
