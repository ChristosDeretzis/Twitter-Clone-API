package com.christos.app.twittercloneapi.repositories;

import com.christos.app.twittercloneapi.models.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query(value = "SELECT * FROM tweet t where t.id = :tweet_id", nativeQuery = true)
    Optional<Tweet> getTweetById(Long tweet_id);

    @Query(value = "SELECT * FROM tweet t where t.user_id = :user_id", nativeQuery = true)
    List<Tweet> getTweetsByUserId(Long user_id);

    // PAGINATION AND SORTING for /tweets

    @Query(value = "select * from tweet order by created_at asc", nativeQuery = true)
    Page<Tweet> findAllAndOrderByCreated_atAsc(Pageable pageable);

    @Query(value = "select * from tweet order by created_at desc", nativeQuery = true)
    Page<Tweet> findAllAndOrderByCreated_atDesc(Pageable pageable);

    @Query(value = "SELECT tweet.* FROM tweet left join likes on tweet.id = likes.tweet_id group by likes.tweet_id order BY COUNT(likes.tweet_id) asc",
            countQuery = "select count(l.tweet_id) from likes l",
            nativeQuery = true)
    Page<Tweet> findAllAndOrderByLikesAsc(Pageable pageable);

    @Query(value = "SELECT tweet.* FROM tweet left join likes on tweet.id = likes.tweet_id group by likes.tweet_id order BY COUNT(likes.tweet_id) desc",
            countQuery = "select count(l.tweet_id) from likes l",
            nativeQuery = true)
    Page<Tweet> findAllAndOrderByLikesDesc(Pageable pageable);

    @Query(value = "select tweet.* from tweet left join comment on tweet.id = comment.tweet_id group by tweet.id order by count(comment.tweet_id) asc",
            countQuery = "select count(c.tweet_id) from comment c",
            nativeQuery = true)
    Page<Tweet> findAllAndOrderByCommentsAsc(Pageable pageable);

    @Query(value = "select tweet.* from tweet left join comment on tweet.id = comment.tweet_id group by tweet.id order by count(comment.tweet_id) desc",
            countQuery = "select count(c.tweet_id) from comment c",
            nativeQuery = true)
    Page<Tweet> findAllAndOrderByCommentsDesc(Pageable pageable);

    @Query(value = "select tweet.* from tweet left join retweet on tweet.id = retweet.tweet_id group by tweet.id order by count(retweet.tweet_id) asc",
            countQuery = "select count(r.tweet_id) from retweet r",
            nativeQuery = true)
    Page<Tweet> findAllAndOrderByRetweetsAsc(Pageable pageable);

    @Query(value = "select tweet.* from tweet left join retweet on tweet.id = retweet.tweet_id group by tweet.id order by count(retweet.tweet_id) desc",
            countQuery = "select count(r.tweet_id) from retweet r",
            nativeQuery = true)
    Page<Tweet> findAllAndOrderByRetweetsDesc(Pageable pageable);


    // PAGINATION AND SORTING for /tweets/by-user/user_id

    @Query(value = "select * from tweet where tweet.user_id = :user_id order by created_at asc", nativeQuery = true)
    Page<Tweet> findAllByUserAndOrderByCreated_atAsc(Long user_id, Pageable pageable);

    @Query(value = "select * from tweet where tweet.user_id = :user_id order by created_at desc", nativeQuery = true)
    Page<Tweet> findAllByUserAndOrderByCreated_atDesc(Long user_id, Pageable pageable);

    @Query(value = "select tweet.* from tweet left join likes on tweet.id = likes.tweet_id where tweet.user_id = :user_id group by tweet.id order by count(likes.tweet_id) asc",
            countQuery = "select count(l.tweet_id) from tweet t left join likes l on t.id = l.tweet_id where t.user_id = :user_id group by t.id",
            nativeQuery = true)
    Page<Tweet> findAllByUserAndOrderByLikesAsc(Long user_id, Pageable pageable);

    @Query(value = "select tweet.* from tweet left join likes on tweet.id = likes.tweet_id where tweet.user_id = :user_id group by tweet.id order by count(likes.tweet_id) desc",
            countQuery = "select count(l.tweet_id) from tweet t left join likes l on t.id = l.tweet_id where t.user_id = :user_id group by t.id",
            nativeQuery = true)
    Page<Tweet> findAllByUserAndOrderByLikesDesc(Long user_id, Pageable pageable);

    @Query(value = "select tweet.* from tweet left join retweet on tweet.id = retweet.tweet_id where tweet.user_id = :user_id group by tweet.id order by count(retweet.tweet_id) asc",
            countQuery = "select count(r.tweet_id) from tweet t join left join r retweet on t.id = r.tweet_id where t.user_id = :user_id group by t.id",
            nativeQuery = true)
    Page<Tweet> findAllByUserAndOrderByRetweetsAsc(Long user_id, Pageable pageable);

    @Query(value = "select tweet.* from tweet left join retweet on tweet.id = retweet.tweet_id where tweet.user_id = :user_id group by tweet.id order by count(retweet.tweet_id) asc",
            countQuery = "select count(r.tweet_id) from tweet t join left join r retweet on t.id = r.tweet_id where t.user_id = :user_id group by t.id",
            nativeQuery = true)
    Page<Tweet> findAllByUserAndOrderByRetweetsDesc(Long user_id, Pageable pageable);

    @Query(value = "select tweet.* from tweet left join comment on tweet.id = comment.tweet_id where tweet.user_id = :user_id group by tweet.id order by count(comment.tweet_id) asc",
            countQuery = "select count(c.tweet_id) from tweet t left join comment c on t.id = c.tweet_id where t.user_id = :user_id group by t.id",
            nativeQuery = true)
    Page<Tweet> findAllByUserAndOrderByCommentsAsc(Long user_id, Pageable pageable);

    @Query(value = "select tweet.* from tweet left join comment on tweet.id = comment.tweet_id where tweet.user_id = :user_id group by tweet.id order by count(comment.tweet_id) desc",
            countQuery = "select count(c.tweet_id) from tweet t left join comment c on t.id = c.tweet_id where t.user_id = :user_id group by t.id",
            nativeQuery = true)
    Page<Tweet> findAllByUserAndOrderByCommentsDesc(Long user_id, Pageable pageable);
}
