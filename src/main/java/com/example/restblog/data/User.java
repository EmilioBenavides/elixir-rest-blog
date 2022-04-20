package com.example.restblog.data;

import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User {
    public enum  Role {USER, ADMIN}

    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate createdAt;
    private Role role;
    Collection<Post> posts;
}
