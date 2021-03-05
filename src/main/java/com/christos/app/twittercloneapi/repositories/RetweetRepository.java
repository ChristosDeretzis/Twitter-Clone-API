package com.christos.app.twittercloneapi.repositories;

import com.christos.app.twittercloneapi.models.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetweetRepository extends JpaRepository<Retweet, Long> {

    @Query(value = "SELECT * FROM retweet r WHERE r.user_id = :userId AND r.tweet_id = :tweetId", nativeQuery = true)
    Retweet getRetweetByUserIdAndTweetId(Long userId, Long tweetId);

    @Query(value = "SELECT * FROM retweet r WHERE r.user_id = :userId", nativeQuery = true)
    List<Retweet> getRetweetsByUserId(Long userId);

    @Query(value = "SELECT * FROM retweet r WHERE r.tweet_id = :tweetId", nativeQuery = true)
    List<Retweet> getRetweetsByTweetId(Long tweetId);
}
