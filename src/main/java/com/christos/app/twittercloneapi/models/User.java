package com.christos.app.twittercloneapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "user")
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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "sex")
    private String sex;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @CreationTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime last_updated_at;

    @ManyToMany
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "user_02"),
            inverseJoinColumns = @JoinColumn(name = "user_01")
    )
    @JsonBackReference
    private Set<User> followers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "user_01"),
            inverseJoinColumns = @JoinColumn(name = "user_02")
    )
    @JsonBackReference
    private Set<User> following = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Tweet> tweets = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Like> likes = new HashSet<>();
}
