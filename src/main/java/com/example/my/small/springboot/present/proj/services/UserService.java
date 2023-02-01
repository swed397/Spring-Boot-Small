package com.example.my.small.springboot.present.proj.services;

import com.example.my.small.springboot.present.proj.entities.UserPrincipal;
import com.example.my.small.springboot.present.proj.interfaces.Crud;
import com.example.my.small.springboot.present.proj.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional(readOnly = true)
    public Optional<UserPrincipal> get(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public UserPrincipal save(UserPrincipal entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(UserPrincipal entity) {
        repository.delete(entity);
    }

    @Transactional(readOnly = true)
    public List<UserPrincipal> findAllByName(String name) {
        return repository.findAllByUserName(name);
    }

    @Transactional
    public Optional<UserPrincipal> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }
}
