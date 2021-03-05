package com.christos.app.twittercloneapi.controllers;

import com.christos.app.twittercloneapi.models.Retweet;
import com.christos.app.twittercloneapi.repositories.RetweetRepository;
import com.christos.app.twittercloneapi.services.RetweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/retweets")
public class RetweetController {

    private RetweetService retweetService;

    @GetMapping
    public List<Retweet> getAllRetweets() {
        return retweetService.getAllRetweets();
    }

    @GetMapping("/by-user/{user_id}")
    public List<Retweet> getAllRetweetsByUserId(@PathVariable("user_id") Long user_id){
        return retweetService.getAllRetweetsByUser(user_id);
    }

    @GetMapping("/by-tweet/{tweet_id}")
    public List<Retweet> getAllRetweetsByTweetId(@PathVariable("tweet_id") Long tweet_id){
        return retweetService.getAllRetweetsByTweet(tweet_id);
    }

    @PostMapping("/by-tweet/{tweet_id}/by-user/{user_id}")
    public ResponseEntity<Retweet> retweetATweet(
            @PathVariable("tweet_id") Long tweet_id,
            @PathVariable("user_id") Long user_id){
        return ResponseEntity.status(HttpStatus.OK).body(retweetService.retweetATweet(tweet_id, user_id));
    }
}
