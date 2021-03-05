package com.christos.app.twittercloneapi.repositories;

import com.christos.app.twittercloneapi.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query(value = "SELECT * FROM likes l WHERE l.user_id = :userId", nativeQuery = true)
    public List<Like> getLikesByUserId(Long userId);

    @Query(value = "SELECT * FROM likes l WHERE l.tweet_id = :tweetId", nativeQuery = true)
    public List<Like> getLikesByTweetId(Long tweetId);

    @Query(value = "SELECT * FROM likes l WHERE l.user_id = :userId AND l.tweet_id = :tweetId", nativeQuery = true)
    public Like getLikeByUserIdAndTweetId(Long userId, Long tweetId);
}
