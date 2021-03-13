package com.christos.app.twittercloneapi.repositories;

import com.christos.app.twittercloneapi.models.User;
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
}
