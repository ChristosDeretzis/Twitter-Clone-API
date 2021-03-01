package com.christos.app.twittercloneapi.controllers;

import com.christos.app.twittercloneapi.exceptions.exceptions.TweetNotFoundException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.services.TweetService;
import com.christos.app.twittercloneapi.services.UserService;
import com.christos.app.twittercloneapi.utils.UpdateJsonUtils;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/tweets")
    public ResponseEntity<Tweet> createTweet(@RequestParam("user_id") Long id, @RequestParam("text") String content) {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException("User with id: " + id + " was not found");
        }

        Tweet newTweet = tweetService.createNewTweet(content, user);
        return ResponseEntity.ok(newTweet);
    }

    @PutMapping("/tweets/{tweet_id}")
    public ResponseEntity<Tweet> updateTweet(@RequestBody Tweet newTweet, @PathVariable("tweet_id") Long id){
        Tweet tweet = tweetService.getTweetById(id);

        if (tweet == null){
            throw new TweetNotFoundException("Tweet with id " + id + " does not exist.");
        }

        UpdateJsonUtils.copyNonNullProperties(newTweet, tweet);
        tweetService.updateTweet(tweet);
        return ResponseEntity.ok(tweet);
    }

    @DeleteMapping("/tweets/{tweet_id}")
    public ResponseEntity<Tweet> deleteTweet(@PathVariable("tweet_id") Long id) {
        Tweet tweet = tweetService.getTweetById(id);

        if (tweet == null){
            throw new TweetNotFoundException("Tweet with id " + id + " does not exist.");
        }

        tweetService.deleteTweet(tweet);
        return ResponseEntity.ok(tweet);
    }
}
