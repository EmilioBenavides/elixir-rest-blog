package com.example.restblog.web;


import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UserController {

    @GetMapping
    private List<User> getAll() {
        ArrayList<User> posts = new ArrayList<>();
        posts.add(new User(1L, "Andy", "andy@gmail.com", "123abc", null, User.Role.USER));//the "L" is to cast that it is a Long integer
        posts.add(new User(2L, "Jorge", "jorge@gmail.com", "456def", null, User.Role.ADMIN));
        posts.add(new User(3L, "Betty", "betty@gmail.com", "789web", null, User.Role.USER));
    return posts;
    }

   // GET /api/posts/5  <-- fetch the blog with id 5
    @GetMapping("{userId}")
    public User getById(@PathVariable Long userId){
        User user = new User(userId, "Andy", "andy@gmail.com", "123abc", null, User.Role.USER);
        return user;
    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {
        System.out.println("Ready to add post: " + newUser);
    }

    @PutMapping("{id}")
    private void updateUser(@PathVariable Long id, @RequestBody User updatePost) {
        updatePost.setId(id);//the id come from the URL path
        System.out.println("Ready to update post: " + updatePost); // we use "oldPost" variable so it can be updated with the request body
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable Long id) {
        System.out.println("Ready to delete post: " + id );
    }

}
