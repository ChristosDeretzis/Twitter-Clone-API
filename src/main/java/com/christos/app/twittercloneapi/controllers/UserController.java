package com.christos.app.twittercloneapi.controllers;

import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
