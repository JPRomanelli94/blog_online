package com.todocode.blog_online.service;

import com.todocode.blog_online.model.Author;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    List<Author> findAll();

    Author findById(Long id);

    void save(Author author);

    void update(Author author);

    void delete(Long id);
}
