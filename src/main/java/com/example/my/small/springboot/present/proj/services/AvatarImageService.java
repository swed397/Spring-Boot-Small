package com.example.my.small.springboot.present.proj.services;

import com.example.my.small.springboot.present.proj.entities.AvatarImage;
import com.example.my.small.springboot.present.proj.entities.UserPrincipal;
import com.example.my.small.springboot.present.proj.interfaces.Crud;
import com.example.my.small.springboot.present.proj.repositories.AvatarImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.*;

@Service
public class AvatarImageService implements Crud<AvatarImage> {
    private static final Logger logger = LoggerFactory.getLogger(AvatarImageService.class);

    private final AvatarImageRepository repository;
    private final UserService userService;

    @Value("${file.storage.path}")
    private String contentLocator;

    @Autowired
    public AvatarImageService(AvatarImageRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public AvatarImage create() {
        return new AvatarImage();
    }

    @Override
    public Optional<AvatarImage> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public AvatarImage save(AvatarImage entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(AvatarImage entity) {
        repository.delete(entity);
    }

    @Transactional
    public void save(String username, String contentType, InputStream is) {
        Optional<AvatarImage> opt = repository.findByUserPrincipal_UserName(username);

        AvatarImage avatarImage;
        String fileName;
        if (opt.isEmpty()) {
            UserPrincipal userPrincipal = userService.findByUserName(username).orElseThrow(IllegalArgumentException::new);

            fileName = UUID.randomUUID().toString();
            avatarImage = new AvatarImage(null, contentType, fileName, userPrincipal);
        } else {
            avatarImage = opt.get();
            fileName = avatarImage.getFilename();
            avatarImage.setContentType(contentType);
        }

        try (OutputStream os = Files.newOutputStream(Path.of(contentLocator, fileName), CREATE, WRITE, TRUNCATE_EXISTING)) {
            is.transferTo(os);

            save(avatarImage);
        } catch (IOException ex) {
            logger.error("Can't write to file {}", fileName, ex);
            throw new IllegalStateException(ex);
        }
    }
}
