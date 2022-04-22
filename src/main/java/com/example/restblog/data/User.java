package com.example.restblog.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    public enum  Role {USER, ADMIN}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Email
    @NotEmpty
    private String email;
//    @Column(nullable = false)
//    @JsonIgnore

    @ToString.Exclude
    private String password;

    @Column(nullable = false)
    private LocalDate createdAt;
//    @Column(nullable = false)

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnoreProperties("author")
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    Collection<Post> posts;

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password= password;
    }
}
