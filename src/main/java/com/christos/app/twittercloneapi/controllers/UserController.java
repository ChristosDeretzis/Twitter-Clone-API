package com.christos.app.twittercloneapi.controllers;

import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userDetailsServiceImpl;

    @GetMapping("/users")
    private List<User> getAllUsers(){
        return userDetailsServiceImpl.getAllUsers();
    }

    @GetMapping("/users/{id}")
    private User getUserById(@PathVariable Long id){
        return userDetailsServiceImpl.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userDetailsServiceImpl.updateUser(id,updatedUser));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userDetailsServiceImpl.deleteUser(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        return ResponseEntity.ok(userDetailsServiceImpl.createUser(newUser));
    }


}
