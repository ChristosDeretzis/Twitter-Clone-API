package com.christos.app.twittercloneapi.services;

import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.repositories.TweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    private TweetRepository tweetRepository;

    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public List<Tweet> getAllTweets(){
        return tweetRepository.findAll();
    }

    public Tweet getTweetById(Long tweet_id) {
        return tweetRepository.getTweetById(tweet_id);
    }

    public List<Tweet> getTweetsByUserId(Long user_id) {
        return tweetRepository.getTweetsByUserId(user_id);
    }
}
