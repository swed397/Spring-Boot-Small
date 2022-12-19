package com.example.my.small.springboot.present.proj.interfaces;

import java.util.Optional;

public interface Crud<T> {
    T create(T entity);

    Optional<T> get(Long id);

    T save(T entity);

    void delete(T entity);
}
