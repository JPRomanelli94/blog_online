package com.todocode.blog_online.service;

import com.todocode.blog_online.model.Post;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface IPostService {
    List<Post> findAll();

    @Nullable Post findById(Long id);

    void save(Post post);

    void update(Post post);

    void delete(Long id);
}
