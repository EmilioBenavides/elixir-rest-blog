package com.example.restblog.web;


import com.example.restblog.data.User;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebListener;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UserController {

    List<User> createUsers() {
        ArrayList<User> posts = new ArrayList<>();
        posts.add(new User(1L, "Andy", "andy@gmail.com", "123abc", null, User.Role.USER));//the "L" is to cast that it is a Long integer
        posts.add(new User(2L, "Jorge", "jorge@gmail.com", "456def", null, User.Role.ADMIN));
        posts.add(new User(3L, "Betty", "betty@gmail.com", "789web", null, User.Role.USER));
        return posts;
    }

    @GetMapping
    private List<User> getAll() {
        return createUsers();
    }

    // GET /api/posts/5  <-- fetch the blog with id 5
    @GetMapping("{userId}")
    public User getById(@PathVariable Long userId) {
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
        System.out.println("Ready to update post: " + updatePost); // we use "updatePost" variable so it can be updated with the request body
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable Long id) {
        System.out.println("Ready to delete post: " + id);
    }

    @GetMapping("username")
    private User getByUserName(@RequestParam String username) {
        User user = new User(1L, username, "andy@gmail.com", "123pol", null, User.Role.USER);
        return user;
    }

    @GetMapping("email")
    private User getByEmail(@RequestParam String email) {
        User user = new User(3L, "Andy", email, "123abc", null, User.Role.USER);
        return user;
    }

    @PutMapping("{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
        if (!oldPassword.equals(newPassword)) { //make sure old and new password don't match so new pw can take its place
            List<User> users = createUsers();
            for (User user : users) { // loop through users
                if (user.getId() == id) {  //make sure user ids match
                    user.setPassword(newPassword);// set old password to new pw.
                }
            }
        }
    }

}
