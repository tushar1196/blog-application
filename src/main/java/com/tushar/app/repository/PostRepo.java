package com.tushar.app.repository;

import com.tushar.app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    @Query(value = "select p from posts p where p.title like %?str%", nativeQuery = true)
    public List<Post> findAllByTitleContaining(@Param("str") String str);

}
