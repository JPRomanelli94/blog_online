package com.todocode.blog_online.repository;

import com.todocode.blog_online.model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserSecRepository extends JpaRepository<UserSec,Long> {
    Optional<UserSec> findUserEntityByUsername(String username);

    boolean existsByUsername(String blogadmin);
}
