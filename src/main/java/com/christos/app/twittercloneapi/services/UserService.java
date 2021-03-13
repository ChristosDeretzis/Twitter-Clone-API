package com.christos.app.twittercloneapi.services;

import com.christos.app.twittercloneapi.exceptions.exceptions.AlreadyExistsException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.repositories.UserRepository;
import com.christos.app.twittercloneapi.utils.UpdateJsonUtils;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.*;

import static java.util.Collections.singletonList;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = userRepository.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("There is no user with id: " + id));

        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        UpdateJsonUtils.copyNonNullProperties(updatedUser, user);
        userRepository.save(user);

        return user;
    }

    public User createUser(User newUser) {
        User user = userRepository.getUserByUsernameOrEmail(newUser.getUsername(), newUser.getEmail());

        if (user != null) {
            throw new AlreadyExistsException("User already exists");
        }

        userRepository.save(newUser);
        return newUser;
    }

    public User deleteUser(Long id) {
        User user = userRepository.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
        return user;
    }
}
