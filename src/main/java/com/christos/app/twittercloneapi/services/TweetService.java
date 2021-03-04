package com.christos.app.twittercloneapi.services;

import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.repositories.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TweetService {

    private TweetRepository tweetRepository;

    public List<Tweet> getAllTweets(){
        return tweetRepository.findAll();
    }

    public Tweet getTweetById(Long tweet_id) {
        return tweetRepository.getTweetById(tweet_id);
    }

    public List<Tweet> getTweetsByUserId(Long user_id) {
        return tweetRepository.getTweetsByUserId(user_id);
    }

    public Tweet createNewTweet(String content, User user) {
        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setContent(content);

        tweetRepository.save(tweet);
        return tweet;
    }

    public void updateTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    public void deleteTweet(Tweet tweet) {
        tweetRepository.delete(tweet);
    }
}
