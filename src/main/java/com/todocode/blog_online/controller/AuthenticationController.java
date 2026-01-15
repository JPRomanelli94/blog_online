package com.todocode.blog_online.controller;

import com.todocode.blog_online.dto.AuthRequestDTO;
import com.todocode.blog_online.dto.AuthResponseDTO;
import com.todocode.blog_online.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO userRequest){
        return new ResponseEntity<>(this.userDetailsServiceImp.loginUser(userRequest), HttpStatus.OK);
    }
}
