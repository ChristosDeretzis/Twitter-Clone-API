package com.christos.app.twittercloneapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "sex")
    private String sex;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @CreationTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime last_updated_;

    @ManyToMany
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "user_02"),
            inverseJoinColumns = @JoinColumn(name = "user_01")
    )
    private List<User> followers;

    @ManyToMany
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "user_01"),
            inverseJoinColumns = @JoinColumn(name = "user_02")
    )
    private List<User> following = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Tweet> tweets = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();
}
