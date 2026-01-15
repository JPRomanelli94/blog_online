package com.todocode.blog_online.service;

import com.todocode.blog_online.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    Optional<Role> findById(Long roleId);

    List<Role> findAll();

    void save(Role role);

    void update(Role rol);
}
