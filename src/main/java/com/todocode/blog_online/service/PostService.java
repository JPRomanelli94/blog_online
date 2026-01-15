package com.todocode.blog_online.service;

import com.todocode.blog_online.model.Post;
import com.todocode.blog_online.repository.IPostRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService{

    @Autowired
    private IPostRepository iPostRepository;

    @Override
    public List<Post> findAll() {
        return iPostRepository.findAll();
    }

    @Override
    public @Nullable Post findById(Long id) {
        return iPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: " + id));
    }

    @Override
    public void save(Post post) {
        iPostRepository.save(post);
    }

    @Override
    public void update(Post post) {
        iPostRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        iPostRepository.deleteById(id);
    }
}
