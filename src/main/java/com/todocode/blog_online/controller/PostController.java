package com.todocode.blog_online.controller;

import com.todocode.blog_online.model.Post;
import com.todocode.blog_online.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        postService.save(post);
        return ResponseEntity.ok(post);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public ResponseEntity<String> updatePost(@RequestBody Post post){
        postService.update(post);
        return ResponseEntity.ok("Post updated succesfully");
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.delete(id);
        return ResponseEntity.ok("Post deleted succesfully");
    }
}
