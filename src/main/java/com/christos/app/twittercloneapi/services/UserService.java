package com.christos.app.twittercloneapi.services;

import com.christos.app.twittercloneapi.controllers.OrderType;
import com.christos.app.twittercloneapi.exceptions.exceptions.AlreadyExistsException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.repositories.UserRepository;
import com.christos.app.twittercloneapi.utils.UpdateJsonUtils;
import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Collections.singletonList;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(int pageNumber, OrderType orderType, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<User> users = null;

        switch (orderType) {
            case DEFAULT:
                users = userRepository.findAll(pageable);
                break;
            case FOLLOWER_COUNT_ASC:
                users = userRepository.findAllOrderByFollowersAsc(pageable);
                break;
            case FOLLOWER_COUNT_DESC:
                users = userRepository.findAllOrderByFollowersDesc(pageable);
                break;
            case LIKE_COUNT_ASC:
                users = userRepository.findAllOrderByLikesAsc(pageable);
                break;
            case LIKE_COUNT_DESC:
                users = userRepository.findAllOrderByLikesDesc(pageable);
                break;
            case RETWEET_COUNT_ASC:
                users = userRepository.findAllOrderByRetweetsAsc(pageable);
                break;
            case RETWEET_COUNT_DESC:
                users = userRepository.findAllOrderByRetweetsDesc(pageable);
                break;
            case TWEET_COUNT_ASC:
                users = userRepository.findAllOrderByTweetsAsc(pageable);
                break;
            case TWEET_COUNT_DESC:
                users = userRepository.findAllOrderByTweetsDesc(pageable);
                break;
        }

        return users.getContent();
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
