package com.example.my.small.springboot.present.proj.repositories;

import com.example.my.small.springboot.present.proj.entities.Movie;
import com.example.my.small.springboot.present.proj.services.MovieService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        movieRepository.deleteAll();
    }

    @Test
    public void testSave() {

        movieService.save(new Movie("TestMovie", 2023));

        Assert.assertTrue(movieService.findAll().size() > 0);
    }

    @Test
    public void testDelete() {
        Movie movie = movieService.save(new Movie("TestMovie", 2023));

        movieService.delete(movie);

        Assert.assertTrue(movieService.findAll().size() == 0);

    }
}
