package com.todocode.blog_online.service;

import com.todocode.blog_online.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    Optional<Permission> findById(long id);

    List<Permission> findAll();

    Permission save(Permission permission);
}
