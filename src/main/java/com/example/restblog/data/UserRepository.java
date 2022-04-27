package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
    User getByEmail(String email);
    User findByEmail(String email);
}
