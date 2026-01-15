package com.todocode.blog_online.controller;

import com.todocode.blog_online.model.Role;
import com.todocode.blog_online.model.UserSec;
import com.todocode.blog_online.service.IRoleService;
import com.todocode.blog_online.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserSecController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserSec> getUsers(){
         return userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id){
        Optional<UserSec> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody UserSec userSec){

        //encriptamos contraseña
        userSec.setPassword(userService.encriptPassword(userSec.getPassword()));

        //Recuperar el/los roles validos
        Set<Role> validRoles = userSec.getRolesList().stream()
                .map(role -> roleService.findById(role.getRole_id()).orElse(null))
                .filter(Objects::nonNull)
                .map(Role.class::cast)
                .collect(Collectors.toSet());

        if (validRoles.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("error","El usuario debe tener al menos un rol valido"));
        }

        //seteamos los roles validos
        userSec.setRolesList(validRoles);

        userService.save(userSec);

        return ResponseEntity.ok(userSec);

    }
}
