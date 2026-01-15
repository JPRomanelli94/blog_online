package com.todocode.blog_online.repository;

import com.todocode.blog_online.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission,Long> {
    Optional<Permission> findByPermissionName(String read);
}
