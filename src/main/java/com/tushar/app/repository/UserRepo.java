package com.tushar.app.repository;

import com.tushar.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select * from users u where u.email=:n and u.password=:p", nativeQuery = true)
    public User findByUserNameAndPassword(@Param("n") String email, @Param("p") String password);

//    @Query(value = "select * from users u where u.id=:p", nativeQuery = true)
//    public User findById(@Param("p") int id);

    public User findById(int id);

}
