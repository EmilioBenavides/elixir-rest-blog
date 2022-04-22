package com.example.restblog.web;


import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import com.example.restblog.data.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebListener;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UserController {

    private UserRepository userRepository;


    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    List<User> createUsers() {
        ArrayList<User> posts = new ArrayList<>();
//        posts.add(new User(1L, "Andy", "andy@gmail.com", "123abc", null, User.Role.USER, null));//the "L" is to cast that it is a Long integer
//        posts.add(new User(2L, "Jorge", "jorge@gmail.com", "456def", null, User.Role.ADMIN, null));
//        posts.add(new User(3L, "Betty", "betty@gmail.com", "789web", null, User.Role.USER, null));
        return posts;
    }

    @GetMapping
    private List<User> getAll() {
        return userRepository.findAll();
    }

    // GET /api/posts/5  <-- fetch the blog with id 5
    @GetMapping("{userId}")
    public Optional<User> getById(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {
        User userToAdd = new User(newUser.getUsername(), newUser.getEmail(), newUser.getPassword());
        userToAdd.setRole(User.Role.USER);
        userToAdd.setCreatedAt(LocalDate.now());
        userRepository.save(userToAdd);
        System.out.println("Ready to add post: " + newUser);
    }

    @PutMapping("{id}")
    private void updateUser(@PathVariable Long userId, @RequestBody User newUser) {
        User userToUpdate = userRepository.getById(userId);//the id come from the URL path
        userToUpdate.setUsername(newUser.getUsername());
        userToUpdate.setEmail(newUser.getEmail());
        userToUpdate.setPassword(newUser.getPassword());
        userToUpdate.setCreatedAt(newUser.getCreatedAt());
        userToUpdate.setRole(newUser.getRole());
        System.out.println("Ready to update post: " + newUser); // we use "updatePost" variable so it can be updated with the request body
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        System.out.println("Ready to delete post: " + userId);
    }

    @GetMapping("username")
    private User getByUsername(@RequestParam String username) {
        return userRepository.getByUsername(username);
    }

    @GetMapping("email")
    private User getByEmail(@RequestParam String email) {
        return userRepository.getByEmail(email);
    }

    @PutMapping("{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
        System.out.printf("Back end wants to update user password for id %d with old pw %s new pw %s\n", id, oldPassword, newPassword);
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
