package com.tushar.app.repository;

import com.tushar.app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {

}
