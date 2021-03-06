package com.christos.app.twittercloneapi.controllers;

import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.services.TweetService;
import com.christos.app.twittercloneapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TweetController {

    private UserService userDetailsServiceImpl;
    private TweetService tweetService;

    @GetMapping("/tweets")
    public List<Tweet> getAllTweets(
            @RequestParam(defaultValue = "4") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "DEFAULT") OrderType orderType
    ) {
        return tweetService.getAllTweets(pageNumber, pageSize, orderType);
    }

    @GetMapping("/tweets/{tweet_id}")
    public Tweet getTweetById(
            @PathVariable("tweet_id"
            ) Long tweet_id) {
        return tweetService.getTweetById(tweet_id);
    }

    @GetMapping("/tweets/by-user/{user_id}")
    public List<Tweet> getTweetsByUserId(
            @PathVariable("user_id") Long id,
            @RequestParam(defaultValue = "4") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "DEFAULT") OrderType orderType) {
        return tweetService.getTweetsByUserId(id, pageNumber, pageSize, orderType);
    }

    @PostMapping("/tweets")
    public ResponseEntity<Tweet> createTweet(@RequestParam("user_id") Long id, @RequestParam("text") String content) {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.createNewTweet(content, id));
    }

    @PutMapping("/tweets/{tweet_id}")
    public ResponseEntity<Tweet> updateTweet(@RequestBody Tweet newTweet, @PathVariable("tweet_id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.updateTweet(id, newTweet));
    }

    @DeleteMapping("/tweets/{tweet_id}")
    public ResponseEntity<Tweet> deleteTweet(@PathVariable("tweet_id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(tweetService.deleteTweet(id));
    }
}
