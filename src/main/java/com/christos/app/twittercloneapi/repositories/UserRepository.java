package com.christos.app.twittercloneapi.repositories;

import com.christos.app.twittercloneapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.* FROM user u where u.id = :id", nativeQuery = true)
    User getUserById(Long id);
}
