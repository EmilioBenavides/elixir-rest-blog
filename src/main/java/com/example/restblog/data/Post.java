package com.example.restblog.data;

import jdk.jfr.Category;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    private Long id;
    private String title;
    private String content;
    private User author;
    private Collection<Category> categories;
}
