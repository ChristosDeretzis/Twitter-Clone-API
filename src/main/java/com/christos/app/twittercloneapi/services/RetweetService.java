package com.christos.app.twittercloneapi.services;

import com.christos.app.twittercloneapi.exceptions.exceptions.AlreadyExistsException;
import com.christos.app.twittercloneapi.exceptions.exceptions.TweetNotFoundException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.Like;
import com.christos.app.twittercloneapi.models.Retweet;
import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.repositories.RetweetRepository;
import com.christos.app.twittercloneapi.repositories.TweetRepository;
import com.christos.app.twittercloneapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RetweetService {
    private RetweetRepository retweetRepository;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    public List<Retweet> getAllRetweets() {
        return retweetRepository.findAll();
    }

    public List<Retweet> getAllRetweetsByUser(Long userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with id: " + userId + " was not found");
        }

        return retweetRepository.getRetweetsByUserId(userId);
    }

    public List<Retweet> getAllRetweetsByTweet(Long tweetId) {
        Tweet tweet = tweetRepository.getTweetById(tweetId);
        if (tweet == null) {
            throw new TweetNotFoundException("Tweet with id: " + tweetId + " was not found");
        }

        return retweetRepository.getRetweetsByTweetId(tweetId);
    }

    public Retweet retweetATweet(Long tweetId, Long userId) {
        User user = userRepository.getUserById(userId);
        Tweet tweet = tweetRepository.getTweetById(tweetId);
        Retweet existsRetweet = retweetRepository.getRetweetByUserIdAndTweetId(userId, tweetId);
        if (user == null) {
            throw new UserNotFoundException("User with id: " + userId + " was not found");
        } else if (tweet == null) {
            throw new TweetNotFoundException("Tweet with id: " + tweetId + " was not found");
        } else if (existsRetweet != null) {
            throw new AlreadyExistsException("Retweet with id: " + existsRetweet.getId() + " already exists");
        }

        Retweet retweet = new Retweet();
        retweet.setTweet(tweet);
        retweet.setUser(user);

        retweetRepository.save(retweet);

        return retweet;
    }
}
