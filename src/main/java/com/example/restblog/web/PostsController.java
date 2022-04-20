package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private PostsRepository postsRepository;

    public PostsController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }


    List<Post> createPosts() {
        List<Post> posts = new ArrayList<>();
//        User author = new User(1L, "Andy", "andy@gmail.com", "123abc", null, User.Role.USER, null);
//        posts.add(new Post(1L, " Post 11111", "Blah blah blah", author));
//        posts.add(new Post(2L, " Post 222222", "Blah blah blah", author));
//        posts.add(new Post(3L, " Post 33333", "Blah blah blah", author));
        return posts;
    }
    @GetMapping
    private List<Post> getAll() {
    return postsRepository.findAll();
    }

   // GET /api/posts/5  <-- fetch the blog with id 5
    @GetMapping("{postId}")
    public Post getById(@PathVariable Long postId){
        Post post = postsRepository.getById(postId);
        return post;
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        Post postToAdd = new Post( newPost.getTitle(), newPost.getContent());
        postsRepository.save(postToAdd);
        System.out.println("Post created");
    }

    @PutMapping("{id}")
    private void createUpdate(@PathVariable Long postId, @RequestBody Post newPost) {
        Post postToUpdate = postsRepository.getById(postId);
        postToUpdate.setContent(newPost.getContent());
        postToUpdate.setTitle(newPost.getTitle());
        System.out.println("Ready to update post: " + newPost); // we use "oldPost" variable so it can be updated with the request body
    }

    @DeleteMapping("{id}")
    private void createDelete(@PathVariable Long postId) {
        Post postToDelete = postsRepository.getById(postId);
        System.out.println("Ready to delete post: " + postId );
    }

}
