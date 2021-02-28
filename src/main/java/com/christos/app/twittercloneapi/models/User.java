package com.christos.app.twittercloneapi.models;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@DynamicUpdate
public class User {

    public User() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "password")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "sex")
    private String sex;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime last_updated_at;

    @ManyToMany
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "user_02"),
            inverseJoinColumns = @JoinColumn(name = "user_01")
    )
    @JsonBackReference(value = "user-followers")
    private List<User> followers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "user_01"),
            inverseJoinColumns = @JoinColumn(name = "user_02")
    )
    @JsonBackReference(value = "user-following")
    private List<User> following = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-tweets")
    private List<Tweet> tweets = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-comments")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-likes")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-retweets")
    private List<Retweet> retweets = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Retweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(List<Retweet> retweets) {
        this.retweets = retweets;
    }
}
