package com.example.my.small.springboot.present.proj.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService service;


    @Test
    void create() {
        assertNotNull(service.create());
    }

}