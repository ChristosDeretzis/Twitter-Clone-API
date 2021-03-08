create database if not exists twitter_clone;
USE twitter_clone;

CREATE TABLE `twitter_clone`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(10) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `last_updated_at` VARCHAR(45) NOT NULL,
  `bio` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
  );
  
CREATE TABLE `twitter_clone`.`tweet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(150) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_tweet_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `twitter_clone`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `twitter_clone`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(150) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `tweet_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tweet_id_idx` (`tweet_id` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `twitter_clone`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_tweet_id`
    FOREIGN KEY (`tweet_id`)
    REFERENCES `twitter_clone`.`tweet` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `twitter_clone`.`follow` (
  `follow_id` INT NOT NULL AUTO_INCREMENT,
  `user_01` INT NOT NULL,
  `user_02` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`follow_id`),
  INDEX `user_01_idx` (`user_01` ASC) VISIBLE,
  INDEX `user_02_idx` (`user_02` ASC) VISIBLE,
  CONSTRAINT `fk_follow_user_01`
    FOREIGN KEY (`user_01`)
    REFERENCES `twitter_clone`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_follow_user_02`
    FOREIGN KEY (`user_02`)
    REFERENCES `twitter_clone`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `unique_follow_pair`
	UNIQUE (`user_01`, `user_02`)
);

CREATE TABLE `twitter_clone`.`likes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tweet_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tweet_id_idx` (`tweet_id` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_like_tweet_id`
    FOREIGN KEY (`tweet_id`)
    REFERENCES `twitter_clone`.`tweet` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_like_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `twitter_clone`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
   CONSTRAINT `Unique_Like`
	 UNIQUE (`tweet_id`, `user_id`)
);

CREATE TABLE `twitter_clone`.`retweet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tweet_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tweet_id_idx` (`tweet_id` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_retweet_tweet_id`
    FOREIGN KEY (`tweet_id`)
    REFERENCES `twitter_clone`.`tweet` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_retweet_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `twitter_clone`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
   CONSTRAINT `Unique_Retweet`
	 UNIQUE (`tweet_id`, `user_id`)
);




  
  
  
  