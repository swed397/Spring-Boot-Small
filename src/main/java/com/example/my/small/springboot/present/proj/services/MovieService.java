package com.example.my.small.springboot.present.proj.services;

import com.example.my.small.springboot.present.proj.entities.Movie;
import com.example.my.small.springboot.present.proj.interfaces.Crud;
import com.example.my.small.springboot.present.proj.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements Crud<Movie> {
    public final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public Movie create() {
        return new Movie();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Movie entity) {
        repository.delete(entity);
    }
}
