package com.example.my.small.springboot.present.proj.repositories;

import com.example.my.small.springboot.present.proj.entities.Movie;
import com.example.my.small.springboot.present.proj.entities.UserPrincipal;
import com.example.my.small.springboot.present.proj.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserService service;

    @BeforeAll
    public void setUp() {
    }

    @Test
    public void testSave() {

        UserPrincipal userPrincipal = service.save(new UserPrincipal("TestUser", "123"));

        Assert.assertNotNull(service.get(userPrincipal.getId()));
    }

    @Test
    public void testDelete() {
        UserPrincipal userToDelete = service.save(new UserPrincipal("User to delete", "123"));

        service.delete(userToDelete);

        Assert.assertTrue(service.findAllByName(userToDelete.getUserName()).size() == 0);

    }
}
