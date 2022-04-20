package com.example.restblog.data;

import jdk.jfr.Category;
import lombok.*;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name= "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 250)
    private String title;
    @Column(nullable = false)
    private String content;
//    private User author;
//    private Collection<Category> categories;

    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }
}
