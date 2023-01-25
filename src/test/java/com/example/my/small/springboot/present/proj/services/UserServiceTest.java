package com.example.my.small.springboot.present.proj.services;

import com.example.my.small.springboot.present.proj.entities.UserPrincipal;
import com.example.my.small.springboot.present.proj.repositories.UserRepository;
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
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeAll
    public void setUp() {
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(new UserPrincipal(1L, "Alex", "password")));
    }

    @Test
    void testCreate() {
        Assert.assertNotNull(userService.create());
    }

    @Test
    public void testGetMethod() {
        UserPrincipal userPrincipal = userService.get(1L).get();

        Assert.assertNotNull(userPrincipal);
        Assert.assertEquals(userPrincipal.getUserName(), "Alex");
        Assert.assertEquals(userPrincipal.getId(), Long.valueOf(1));
        Assert.assertEquals(userPrincipal.getPassword(), "password");
    }
}