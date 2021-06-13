package com.tushar.app.repository;

import com.tushar.app.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {
}
