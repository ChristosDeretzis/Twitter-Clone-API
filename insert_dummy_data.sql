use twitter_clone;

insert into user(username, first_name, last_name, email, password, sex, created_at, last_updated_at) values ('george87', 'Georgios','Papadopoulos', 'georgios.papadopoulos@gmail.com', 'wert23!2', 'Male', NOW(), NOW());
insert into user(username, first_name, last_name, email, password, sex, created_at, last_updated_at) values ('masaBear', 'Venetia','Papadopoulou', 'venetia.papad@gmail.com', 'user12345', 'Female', NOW(), NOW());
insert into user(username, first_name, last_name, email, password, sex, created_at, last_updated_at) values ('hailey.Fan98', 'Maria','Antoniadou', 'maria.antoniadou@gmail.com', 'soapen23!re', 'Female', NOW(), NOW());
insert into user(username, first_name, last_name, email, password, sex, created_at, last_updated_at) values ('paok.giaPanta', 'Nikos','Andrioglou', 'nikos.andrio89@gmail.com', 'leming87$4left', 'Male', NOW(), NOW());
insert into user(username, first_name, last_name, email, password, sex, created_at, last_updated_at) values ('gayLover98', 'Christos','Georgiou', 'Christos.Georgiou@gmail.com', 'lock37@@#fge6', 'Male', NOW(), NOW());

insert into tweet(content, created_at, user_id) values ('BTS new song is available. #BTS', NOW(), 2);
insert into tweet(content, created_at, user_id) values ('PAOK gia panta', NOW(), 4);
insert into tweet(content, created_at, user_id) values ('WTF is going on to america ?', NOW(), 2);
insert into tweet(content, created_at, user_id) values ('Hello everyone! New here!', NOW(), 3);
insert into tweet(content, created_at, user_id) values ('Like it or not, I am gay', NOW(), 5);
insert into tweet(content, created_at, user_id) values ('Hailey is a bicon!!', NOW(), 3);

insert into comment(content, created_at, tweet_id, user_id) values ('Μονο ολυμπιακός', NOW(), 2, 1);
insert into comment(content, created_at, tweet_id, user_id) values ('Ax eleos', NOW(), 2, 2);
insert into comment(content, created_at, tweet_id, user_id) values ('OMG, I will listen to it right now', NOW(), 1, 1);
insert into comment(content, created_at, tweet_id, user_id) values ('Go Army #BTS', NOW(), 1, 2);
insert into comment(content, created_at, tweet_id, user_id) values ('Love KPOP!! #BTS', NOW(), 1, 3);
insert into comment(content, created_at, tweet_id, user_id) values ('Some people entered in capitol', NOW(), 3, 5);
insert into comment(content, created_at, tweet_id, user_id) values ('Lots of chaos', NOW(), 3, 2);
insert into comment(content, created_at, tweet_id, user_id) values ('Welcome to twitter Family', NOW(), 4, 1);
insert into comment(content, created_at, tweet_id, user_id) values ('Hello girl, welcome!', NOW(), 4, 4);

insert into follow(user_01, user_02, created_at) values(1, 2, NOW());
insert into follow(user_01, user_02, created_at) values(1, 4, NOW());
insert into follow(user_01, user_02, created_at) values(2, 1, NOW());
insert into follow(user_01, user_02, created_at) values(2, 3, NOW());
insert into follow(user_01, user_02, created_at) values(3, 2, NOW());
insert into follow(user_01, user_02, created_at) values(3, 4, NOW());
insert into follow(user_01, user_02, created_at) values(3, 5, NOW());
insert into follow(user_01, user_02, created_at) values(4, 5, NOW());
insert into follow(user_01, user_02, created_at) values(5, 2, NOW());
insert into follow(user_01, user_02, created_at) values(5, 3, NOW());

insert into twitter_clone.like(user_id, tweet_id, created_at) values (1, 1, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (1, 3, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (2, 3, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (2, 4, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (2, 5, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (3, 2, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (4, 1, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (4, 2, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (4, 5, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (4, 6, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (5, 3, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (5, 4, NOW());
insert into twitter_clone.like(user_id, tweet_id, created_at) values (5, 6, NOW());

insert into retweet (user_id, tweet_id, created_at) values (1, 3, NOW());
insert into retweet (user_id, tweet_id, created_at) values (2, 3, NOW());
insert into retweet (user_id, tweet_id, created_at) values (2, 4, NOW());
insert into retweet (user_id, tweet_id, created_at) values (2, 5, NOW());
insert into retweet (user_id, tweet_id, created_at) values (3, 6, NOW());
insert into retweet (user_id, tweet_id, created_at) values (4, 6, NOW());
insert into retweet (user_id, tweet_id, created_at) values (4, 1, NOW());
