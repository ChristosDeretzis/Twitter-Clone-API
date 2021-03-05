package com.christos.app.twittercloneapi.controllers;

import com.christos.app.twittercloneapi.exceptions.exceptions.AlreadyExistsException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.services.UserService;
import com.christos.app.twittercloneapi.utils.UpdateJsonUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    private User getUserById(@PathVariable Long id){
        User user =  userService.getUserById(id);

        if (user == null){
            throw new UserNotFoundException("There is no user with id: " + id);
        }

        return user;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        UpdateJsonUtils.copyNonNullProperties(updatedUser, user);
        userService.updateOrSaveUser(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        userService.deleteUser(user);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        User user = userService.getUserByEmailOrUsername(newUser.getUsername(), newUser.getEmail());

        if (user != null) {
            throw new AlreadyExistsException("User already exists");
        }

        userService.updateOrSaveUser(newUser);
        return ResponseEntity.ok(newUser);
    }


}
