package com.tushar.app.repository;

import com.tushar.app.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository<Posts,Integer> {
}
