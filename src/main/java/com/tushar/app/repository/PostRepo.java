package com.tushar.app.repository;

import com.tushar.app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    @Query(value = "select * from posts where title like %?1% or author like %?1% or content like %?1%" , nativeQuery = true)
    List<Post> findAllByTitleContaining(String search);

}
