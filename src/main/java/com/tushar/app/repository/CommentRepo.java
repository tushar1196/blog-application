package com.tushar.app.repository;

import com.tushar.app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByPostId(int postId);

}
