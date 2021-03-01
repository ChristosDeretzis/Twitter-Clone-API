package com.christos.app.twittercloneapi.repositories;

import com.christos.app.twittercloneapi.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query(value = "SELECT * FROM tweet t where t.id = :tweet_id", nativeQuery = true)
    Tweet getTweetById(Long tweet_id);

    @Query(value = "SELECT * FROM tweet t where t.user_id = :user_id", nativeQuery = true)
    List<Tweet> getTweetsByUserId(Long user_id);
}
