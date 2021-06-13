package com.tushar.crud.repository;

import com.tushar.crud.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {
}
