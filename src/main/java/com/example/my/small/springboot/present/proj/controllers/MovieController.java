package com.example.my.small.springboot.present.proj.controllers;

import com.example.my.small.springboot.present.proj.dtos.movies.MovieCreateDto;
import com.example.my.small.springboot.present.proj.dtos.movies.MovieDto;
import com.example.my.small.springboot.present.proj.entities.Movie;
import com.example.my.small.springboot.present.proj.mappers.MovieMapper;
import com.example.my.small.springboot.present.proj.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/movies")
@Api
public class MovieController {

    public final MovieService service;
    public final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService service, MovieMapper movieMapper) {
        this.service = service;
        this.movieMapper = movieMapper;
    }

    @GetMapping("")
    @ApiOperation("Get all movies from bd")
    public ResponseEntity<List<MovieDto>> findAll() {
        log.trace("Getting all films");
        return ResponseEntity.ok(service.findAll().stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one movie from bd by id")
    public ResponseEntity<MovieDto> findById(@PathVariable Long id) {
        log.trace("Get film by " + id + " id");
        return ResponseEntity.of(service.get(id).map(movieMapper::toDto));
    }

    @PostMapping("")
    @ApiOperation("Insert new film in bd")
    public ResponseEntity<MovieDto> save(@RequestBody @Valid MovieCreateDto movieCreateDto) {
        log.trace("Created new film with name: " + movieCreateDto.getTitle());
        Movie movie = service.save(movieMapper.toEntity(movieCreateDto));
        return ResponseEntity.ok(movieMapper.toDto(movie));
    }
}
