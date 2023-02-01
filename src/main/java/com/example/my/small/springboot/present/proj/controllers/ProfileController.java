package com.example.my.small.springboot.present.proj.controllers;

import com.example.my.small.springboot.present.proj.configs.exceptions.InternalServerError;
import com.example.my.small.springboot.present.proj.services.AvatarImageService;
import com.example.my.small.springboot.present.proj.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/profile")
//@Secured("ROLE_ADMIN")
@Api
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final UserService userService;
    private final AvatarImageService avatarImageService;

    @Autowired
    public ProfileController(UserService userService, AvatarImageService avatarImageService) {
        this.userService = userService;
        this.avatarImageService = avatarImageService;
    }

    @PostMapping("/avatar")
    @ApiOperation("Update or download avatar")
    public String updateAvatarImage(Authentication authentication, @RequestParam("avatar") MultipartFile avatar) throws InternalServerError {
        logger.info("File name {}, file content type {}, file size {}",
                avatar.getOriginalFilename(), avatar.getContentType(), avatar.getSize());

        try {
            avatarImageService.save(authentication.getName(), avatar.getContentType(), avatar.getInputStream());
        } catch (IOException e) {
            logger.info("", e);
            throw new InternalServerError();
        }

        return "Success";
    }
}
