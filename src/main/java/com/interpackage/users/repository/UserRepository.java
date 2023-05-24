package com.interpackage.users.repository;

import java.util.List;
import java.util.Optional;

import com.interpackage.users.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import com.interpackage.users.model.User;

@Repository
@RequestScope
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    List<User> findAllByRoleIdRole(Long id);
    Optional<User> findByDpiAndRole(String dpi, Role role);
}
