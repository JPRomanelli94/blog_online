package com.todocode.blog_online.controller;

import com.todocode.blog_online.model.Author;
import com.todocode.blog_online.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @GetMapping
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        authorService.save(author);
        return ResponseEntity.ok(author);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateAuthor(@RequestBody Author author){
        authorService.update(author);
        return ResponseEntity.ok("Author updated succesfully");
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        authorService.delete(id);
        return ResponseEntity.ok("Author deleted succesfully");
    }
}
