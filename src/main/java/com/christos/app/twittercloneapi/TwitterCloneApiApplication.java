package com.christos.app.twittercloneapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TwitterCloneApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(TwitterCloneApiApplication.class, args);
    }

}
