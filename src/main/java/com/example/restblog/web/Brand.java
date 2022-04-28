package com.example.restblog.web;


import com.example.restblog.data.Guitar;
import com.example.restblog.data.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name= "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnoreProperties({"brand"})
    Collection<Guitar> guitars;
}

