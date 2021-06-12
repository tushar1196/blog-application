package com.tushar.crud.repository;

import com.tushar.crud.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository<Posts,Integer> {
}
