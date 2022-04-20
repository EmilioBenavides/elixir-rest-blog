package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> { //the have to match the data type and the type of primary key

}
