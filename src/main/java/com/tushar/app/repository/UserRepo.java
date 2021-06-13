package com.tushar.app.repository;

import com.tushar.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<Users, Integer> {
}
