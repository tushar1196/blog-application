package com.tushar.crud.repository;

import com.tushar.crud.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<Users, Integer> {
}
