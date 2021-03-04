package com.christos.app.twittercloneapi.services;

import com.christos.app.twittercloneapi.exceptions.exceptions.TweetNotFoundException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.Comment;
import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.hibernate.type.descriptor.spi.JdbcRecommendedSqlTypeMappingContext;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private TweetService tweetService;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllCommentsByUserId(Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with id: "+ userId + " does not exist");
        }
        List<Comment> commentsByUser = commentRepository.getAllCommentsByUserId(userId);
        return commentsByUser;
    }

    public List<Comment> getAllCommentsByTweetId(Long tweetId){
        Tweet tweet = tweetService.getTweetById(tweetId);
        if (tweet == null) {
            throw new TweetNotFoundException("Tweet with id: " + tweetId + " does not exist");
        }
        List<Comment> commentsByTweet = commentRepository.getAllCommentsByTweetId(tweetId);
        return commentsByTweet;
    }

    public Comment createComment(String text, Long userId, Long tweetId) {
        User user = userService.getUserById(userId);
        Tweet tweet = tweetService.getTweetById(tweetId);
        if (user == null) {
            throw new UserNotFoundException("User with id: "+ userId + " does not exist");
        } else if (tweet == null) {
            throw new TweetNotFoundException("Tweet with id: " + tweetId + " does not exist");
        }

        Comment comment = new Comment();
        comment.setContent(text);
        comment.setUser(user);
        comment.setTweet(tweet);
        commentRepository.save(comment);

        return comment;
    }
}
