package com.christos.app.twittercloneapi.controllers;

import com.christos.app.twittercloneapi.exceptions.exceptions.TweetNotFoundException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.services.TweetService;
import com.christos.app.twittercloneapi.services.UserService;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TweetController {

    private UserService userService;
    private TweetService tweetService;

    public TweetController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @GetMapping("/tweets")
    public List<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/tweets/{tweet_id}")
    public Tweet getTweetById(@PathVariable("tweet_id") Long tweet_id) {
        Tweet tweet = tweetService.getTweetById(tweet_id);

        if (tweet == null){
            throw new TweetNotFoundException("Tweet with id " + tweet_id + " does not exist.");
        }

        return tweet;
    }

    @GetMapping("/users/{user_id}/tweets")
    public List<Tweet> getTweetsByUserId(@PathVariable("user_id") Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException("User with id: " + id + " was not found");
        }

        List<Tweet> userTweets = tweetService.getTweetsByUserId(id);
        return userTweets;
    }


}
