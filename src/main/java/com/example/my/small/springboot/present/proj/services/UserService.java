package com.example.my.small.springboot.present.proj.services;

import com.example.my.small.springboot.present.proj.entities.UserPrincipal;
import com.example.my.small.springboot.present.proj.interfaces.Crud;
import com.example.my.small.springboot.present.proj.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements Crud<UserPrincipal> {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserPrincipal create() {
        return new UserPrincipal();
    }

    @Override
    public Optional<UserPrincipal> get(Long id) {
        return Optional.of(repository.getById(id));
    }

    @Override
    public UserPrincipal save(UserPrincipal entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(UserPrincipal entity) {
        repository.delete(entity);
    }
}
