package com.christos.app.twittercloneapi.repositories;

import com.christos.app.twittercloneapi.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment c WHERE c.user_id = :userId", nativeQuery = true)
    List<Comment> getAllCommentsByUserId(Long userId);

    @Query(value = "SELECT * FROM comment c WHERE c.tweet_id= :tweetId", nativeQuery = true)
    List<Comment> getAllCommentsByTweetId(Long tweetId);
}
