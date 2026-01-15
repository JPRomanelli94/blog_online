package com.todocode.blog_online.service;

import com.todocode.blog_online.model.Permission;
import com.todocode.blog_online.repository.IPermissionRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService{

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public Optional<Permission> findById(long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }
}
