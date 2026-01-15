package com.todocode.blog_online.service;

import com.todocode.blog_online.model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserSec> findAll();

    Optional<UserSec> findById(Long id);

    String encriptPassword(String password);

    void save(UserSec userSec);
}
