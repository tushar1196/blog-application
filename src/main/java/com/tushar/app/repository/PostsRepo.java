package com.tushar.app.repository;

import com.tushar.app.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepo extends JpaRepository<Posts,Integer> {
    
}
