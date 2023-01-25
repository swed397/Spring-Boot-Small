package com.example.my.small.springboot.present.proj.services;

import com.example.my.small.springboot.present.proj.entities.Movie;
import com.example.my.small.springboot.present.proj.repositories.MovieRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieServiceTest {

    @Autowired
    public MovieService service;
    @MockBean
    public MovieRepository repository;

    @BeforeAll
    public void setUp() {
        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(new Movie(1L, "Test film", 1990)));

    }

    @Test
    public void getMethodNotNullIfPresents() {
        Movie movie = service.get(1L).get();

        Assert.assertNotNull(movie);
        Assert.assertEquals(movie.getId().longValue(), 1L);
        Assert.assertEquals(movie.getTitle(), "Test film");
        Assert.assertEquals(movie.getYear(), Integer.valueOf(1990));
    }

    @Test
    public void findAllMethodReturn() {
        Assert.assertNotNull(service.findAll());
    }
}