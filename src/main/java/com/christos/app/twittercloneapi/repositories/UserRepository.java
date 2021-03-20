package com.christos.app.twittercloneapi.repositories;

import com.christos.app.twittercloneapi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.* FROM user u WHERE u.id = :id", nativeQuery = true)
    Optional<User> getUserById(Long id);

    @Query(value = "SELECT u.* FROM user u WHERE u.username = :username OR u.email = :email", nativeQuery = true)
    User getUserByUsernameOrEmail(String username, String email);

    @Query(value = "SELECT u.* FROM user u WHERE u.username = :username", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "select user.* from user left join follow on user.id = follow.user_02 group by follow.user_02 order by COUNT(follow.user_02) asc", nativeQuery = true)
    Page<User> findAllOrderByFollowersAsc(Pageable pageable);

    @Query(value = "select user.* from user left join follow on user.id = follow.user_02 group by follow.user_02 order by COUNT(follow.user_02) desc", nativeQuery = true)
    Page<User> findAllOrderByFollowersDesc(Pageable pageable);

    @Query(value = "select user.* from user left join tweet on user.id = tweet.user_id group by tweet.user_id order by COUNT(tweet.user_id) asc", nativeQuery = true)
    Page<User> findAllOrderByTweetsAsc(Pageable pageable);

    @Query(value = "select user.* from user left join tweet on user.id = tweet.user_id group by tweet.user_id order by COUNT(tweet.user_id) desc", nativeQuery = true)
    Page<User> findAllOrderByTweetsDesc(Pageable pageable);

    @Query(value = "select distinct user.* from user left join tweet on tweet.user_id = user.id left join likes on tweet.id = likes.tweet_id group by user.id order by count(user.id) asc", nativeQuery = true)
    Page<User> findAllOrderByLikesAsc(Pageable pageable);

    @Query(value = "select distinct user.* from user left join tweet on tweet.user_id = user.id left join likes on tweet.id = likes.tweet_id group by user.id order by count(user.id) desc", nativeQuery = true)
    Page<User> findAllOrderByLikesDesc(Pageable pageable);

    @Query(value = "select distinct user.* from user left join tweet on tweet.user_id = user.id left join retweet on tweet.id = retweet.tweet_id group by user.id order by count(user.id) asc", nativeQuery = true)
    Page<User> findAllOrderByRetweetsAsc(Pageable pageable);

    @Query(value = "select distinct user.* from user left join tweet on tweet.user_id = user.id left join retweet on tweet.id = retweet.tweet_id group by user.id order by count(user.id) desc", nativeQuery = true)
    Page<User> findAllOrderByRetweetsDesc(Pageable pageable);
}
