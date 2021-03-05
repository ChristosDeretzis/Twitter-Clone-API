package com.christos.app.twittercloneapi.controllers;

import com.christos.app.twittercloneapi.models.Like;
import com.christos.app.twittercloneapi.services.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {

    private LikeService likeService;

    @GetMapping
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/by-user/{user_id}")
    public List<Like> getAllLikesByUser(@PathVariable("user_id") Long userId) {
        return likeService.getAllLikesByUser(userId);
    }

    @GetMapping("/by-tweet/{tweet_id}")
    public List<Like> getAllLikesByTweet(@PathVariable("tweet_id") Long tweetId) {
        return likeService.getAllLikesByTweet(tweetId);
    }

    @PostMapping("/by-tweet/{tweet_id}/by-user/{user_id}")
    public ResponseEntity<Like> likeATweet(
            @PathVariable("tweet_id") Long tweetId,
            @PathVariable("user_id") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(likeService.likeATweet(tweetId, userId));
    }
}
