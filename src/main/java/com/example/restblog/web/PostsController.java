package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    @GetMapping
    private List<Post> getAll() {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, " Post 11111", "Blah blah blah"));//the "L" is to cast that it is a Long integer
        posts.add(new Post(2L, " Post 222222", "Blah blah blah"));
        posts.add(new Post(3L, " Post 33333", "Blah blah blah"));
    return posts;
    }

   // GET /api/posts/5  <-- fetch the blog with id 5
    @GetMapping("{postId}")
    public Post getById(@PathVariable Long postId){
        Post post = new Post(postId, "Post " + postId, "Blah blah blah");
        return post;
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        System.out.println("Ready to add post: " + newPost);
    }

    @PutMapping("{id}")
    private void createUpdate(@PathVariable Long id, @RequestBody Post updatePost) {
        Post oldPost = new Post(); //this would be the pre-existing data
        oldPost.setTitle(updatePost.getTitle());//you want to update all properties
        oldPost.setContent(updatePost.getContent());
        oldPost.setId(id);//the id come from the URL path
        System.out.println("Ready to update post: " + oldPost); // we use "oldPost variable so it can be updated with the request body
    }

    @DeleteMapping("{id}")
    private void createDelete(@PathVariable Long id) {
        System.out.println("Ready to delete post: " + id );
    }

}
