package com.christos.app.twittercloneapi.services;

import com.christos.app.twittercloneapi.exceptions.exceptions.TweetNotFoundException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.repositories.TweetRepository;
import com.christos.app.twittercloneapi.utils.UpdateJsonUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TweetService {

    private TweetRepository tweetRepository;
    private UserService userDetailsServiceImpl;

    public List<Tweet> getAllTweets(){
        return tweetRepository.findAll();
    }

    public Tweet getTweetById(Long tweet_id) {
        Tweet tweet = tweetRepository.getTweetById(tweet_id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet with id " + tweet_id + " does not exist."));

        return tweet;
    }

    public List<Tweet> getTweetsByUserId(Long user_id) {
        User user = userDetailsServiceImpl.getUserById(user_id);

        if (user == null) {
            throw new UserNotFoundException("User with id: " + user_id + " was not found");
        }

        List<Tweet> tweets = tweetRepository.getTweetsByUserId(user_id);
        return tweets;
    }

    public Tweet createNewTweet(String content, Long user_id) {
        User user = userDetailsServiceImpl.getUserById(user_id);

        if (user == null) {
            throw new UserNotFoundException("User with id: " + user_id + " was not found");
        }

        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setContent(content);

        tweetRepository.save(tweet);
        return tweet;
    }

    public Tweet updateTweet(Long id, Tweet newTweet) {
        Tweet tweet = tweetRepository.getTweetById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet with id " + id + " does not exist."));

        UpdateJsonUtils.copyNonNullProperties(newTweet, tweet);
        tweetRepository.save(tweet);

        return tweet;
    }

    public Tweet deleteTweet(Long id) {
        Tweet tweet = tweetRepository.getTweetById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet with id " + id + " does not exist."));

        tweetRepository.delete(tweet);

        return tweet;
    }
}
