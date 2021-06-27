package com.tushar.app.repository;

import com.tushar.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from users u where u.email=:email", nativeQuery = true)
    User findByUserName(@Param("email") String email);

    User findById(int id);

    List<User> findAll();

//    User findByEmail(String username);

}
