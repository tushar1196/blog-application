package com.tushar.app.repository;

import com.tushar.app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    @Query(value ="SELECT * FROM posts p INNER JOIN post_tags pt ON p.id = pt.post_id INNER JOIN tags t ON pt.tag_id = t.id where p.title like %?1% or p.author like %?1% or p.content like %?1% or t.name like %?1%",nativeQuery = true )
    List<Post> findAllBySearch(String search);

    @Query(value = "select DISTINCT(p.author) from posts p", nativeQuery = true)
    List<String> finaDistinctAuthors();

    @Query(value ="SELECT * FROM posts p INNER JOIN post_tags pt ON p.id = pt.post_id INNER JOIN tags t ON pt.tag_id = t.id where t.id IN ?1" , nativeQuery = true )
    List<Post> findAllByTagId(List<Integer> id);
}
