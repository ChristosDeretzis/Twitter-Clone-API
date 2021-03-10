package com.christos.app.twittercloneapi.services;

import com.christos.app.twittercloneapi.exceptions.exceptions.AlreadyExistsException;
import com.christos.app.twittercloneapi.exceptions.exceptions.TweetNotFoundException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.Like;
import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.repositories.LikeRepository;
import com.christos.app.twittercloneapi.repositories.TweetRepository;
import com.christos.app.twittercloneapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public List<Like> getAllLikesByUser(Long userId) {
        User user = userRepository.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " was not found"));

        return likeRepository.getLikesByUserId(userId);
    }

    public List<Like> getAllLikesByTweet(Long tweetId) {
        Tweet tweet = tweetRepository.getTweetById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet with id: " + tweetId + " was not found"));

        return likeRepository.getLikesByTweetId(tweetId);
    }

    public Like likeATweet(Long tweetId, Long userId) {

        User user = userRepository.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " was not found"));

        Tweet tweet = tweetRepository.getTweetById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet with id: " + tweetId + " was not found"));

        Like existsLike = likeRepository.getLikeByUserIdAndTweetId(userId, tweetId);

        if (existsLike != null) {
            throw new AlreadyExistsException("Like with id: " + existsLike.getId() + " already exists");
        }

        Like like = new Like();
        like.setTweet(tweet);
        like.setUser(user);

        likeRepository.save(like);

        return like;
    }
}
