package com.example.restblog.web;

import com.example.restblog.data.*;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.restblog.services.EmailServices;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private PostsRepository postsRepository;
    private final UserRepository usersRepository;
    private CategoriesRepository categoriesRepository;
    private final EmailServices emailServices;

    public PostsController(PostsRepository postsRepository, UserRepository userRepository, CategoriesRepository categoriesRepository, EmailServices emailServices) {
        this.postsRepository = postsRepository;
        this.usersRepository = userRepository;
        this.categoriesRepository = categoriesRepository;
        this.emailServices = emailServices;
    }

    @GetMapping
    private List<Post> getAll() {
    return postsRepository.findAll();
    }

   // GET /api/posts/5  <-- fetch the blog with id 5
    @GetMapping("{postId}")
    public Post getById(@PathVariable Long postId){
        return postsRepository.getById(postId);
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost, OAuth2Authentication auth) {
//        newPost.setAuthor(usersRepository.getById(1L));
        String email = auth.getName();
        User author = usersRepository.findByEmail(email);
        List<Category> categories = new ArrayList<>();
        categories.add(categoriesRepository.findCategoryByName("food"));
        categories.add(categoriesRepository.findCategoryByName("music"));
        newPost.setCategories(categories);
        postsRepository.save(newPost);
        emailServices.prepareAndSend(newPost,"wes","Work!");
        System.out.println("new Post Created");
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
        postsRepository.deleteById(postId);
        System.out.println("Ready to delete post: " + postId );
    }

}
