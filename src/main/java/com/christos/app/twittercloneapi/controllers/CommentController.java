package com.christos.app.twittercloneapi.controllers;

import com.christos.app.twittercloneapi.models.Comment;
import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/by-user/{user_id}")
    public List<Comment> getAllCommentsByUserId(@PathVariable("user_id") Long user_id) {
        return commentService.getAllCommentsByUserId(user_id);
    }

    @GetMapping("/by-tweet/{tweet_id}")
    public List<Comment> getAllCommentsByTweetId(@PathVariable("tweet_id") Long tweet_id) {
        return commentService.getAllCommentsByTweetId(tweet_id);
    }

    @PostMapping("/by-user/{user_id}/by-tweet/{tweet_id}")
    public ResponseEntity<Comment> createComment(
            @PathVariable("user_id") Long user_id,
            @PathVariable("tweet_id") Long tweet_id,
            @RequestParam("text") String text) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.createComment(text, user_id, tweet_id));
    }
}
