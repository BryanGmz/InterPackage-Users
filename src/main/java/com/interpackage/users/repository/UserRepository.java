package com.interpackage.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import com.interpackage.users.model.User;

@Repository
@RequestScope
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
