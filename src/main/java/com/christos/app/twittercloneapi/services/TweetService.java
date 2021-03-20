package com.christos.app.twittercloneapi.services;

import com.christos.app.twittercloneapi.controllers.OrderType;
import com.christos.app.twittercloneapi.exceptions.exceptions.TweetNotFoundException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import com.christos.app.twittercloneapi.models.Tweet;
import com.christos.app.twittercloneapi.models.User;
import com.christos.app.twittercloneapi.repositories.TweetRepository;
import com.christos.app.twittercloneapi.utils.UpdateJsonUtils;
import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TweetService {

    private TweetRepository tweetRepository;
    private UserService userDetailsServiceImpl;

    public List<Tweet> getAllTweets(int pageNumber, int pageSize, OrderType orderType){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Tweet> tweets = null;

        switch (orderType) {
            case DEFAULT:
                tweets = tweetRepository.findAll(pageable);
                break;
            case POST_DATE_ASC:
                tweets = tweetRepository.findAllAndOrderByCreated_atAsc(pageable);
                break;
            case POST_DATE_DESC:
                tweets = tweetRepository.findAllAndOrderByCreated_atDesc(pageable);
                break;
            case LIKE_COUNT_ASC:
                tweets = tweetRepository.findAllAndOrderByLikesAsc(pageable);
                break;
            case LIKE_COUNT_DESC:
                tweets = tweetRepository.findAllAndOrderByLikesDesc(pageable);
                break;
            case RETWEET_COUNT_ASC:
                tweets = tweetRepository.findAllAndOrderByRetweetsAsc(pageable);
                break;
            case RETWEET_COUNT_DESC:
                tweets = tweetRepository.findAllAndOrderByRetweetsDesc(pageable);
                break;
            case COMMENT_COUNT_ASC:
                tweets = tweetRepository.findAllAndOrderByCommentsAsc(pageable);
                break;
            case COMMENT_COUNT_DESC:
                tweets = tweetRepository.findAllAndOrderByCommentsDesc(pageable);
                break;
        }

        return tweets.getContent();
    }

    public Tweet getTweetById(Long tweet_id) {
        Tweet tweet = tweetRepository.getTweetById(tweet_id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet with id " + tweet_id + " does not exist."));

        return tweet;
    }

    public List<Tweet> getTweetsByUserId(Long user_id, int pageNumber, int pageSize, OrderType orderType) {
        User user = userDetailsServiceImpl.getUserById(user_id);

        if (user == null) {
            throw new UserNotFoundException("User with id: " + user_id + " was not found");
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Tweet> tweets = null;

        switch (orderType) {
            case DEFAULT:
                tweets = tweetRepository.findAllByUser(user_id, pageable);
                break;
            case POST_DATE_ASC:
                tweets = tweetRepository.findAllByUserAndOrderByCreated_atAsc(user_id, pageable);
                break;
            case POST_DATE_DESC:
                tweets = tweetRepository.findAllByUserAndOrderByCreated_atDesc(user_id, pageable);
                break;
            case LIKE_COUNT_ASC:
                tweets = tweetRepository.findAllByUserAndOrderByLikesAsc(user_id, pageable);
                break;
            case LIKE_COUNT_DESC:
                tweets = tweetRepository.findAllByUserAndOrderByLikesDesc(user_id, pageable);
                break;
            case RETWEET_COUNT_ASC:
                tweets = tweetRepository.findAllByUserAndOrderByRetweetsAsc(user_id, pageable);
                break;
            case RETWEET_COUNT_DESC:
                tweets = tweetRepository.findAllByUserAndOrderByRetweetsDesc(user_id, pageable);
                break;
            case COMMENT_COUNT_ASC:
                tweets = tweetRepository.findAllByUserAndOrderByCommentsAsc(user_id, pageable);
                break;
            case COMMENT_COUNT_DESC:
                tweets = tweetRepository.findAllByUserAndOrderByCommentsDesc(user_id, pageable);
                break;
        }

        return tweets.getContent();
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
