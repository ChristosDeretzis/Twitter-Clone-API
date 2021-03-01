package com.christos.app.twittercloneapi.repositories;

import com.christos.app.twittercloneapi.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
