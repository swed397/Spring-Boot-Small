package com.example.my.small.springboot.present.proj.controllers;

import com.example.my.small.springboot.present.proj.dtos.user.UserDto;
import com.example.my.small.springboot.present.proj.mappers.UserMapper;
import com.example.my.small.springboot.present.proj.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@Secured("ROLE_ADMIN")
@Api
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one movie from bd by id")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        log.trace("Get user by " + id + " id");
        return ResponseEntity.of(userService.get(id).map(userMapper::toDto));
    }
}
