package com.tushar.app.repository;

import com.tushar.app.model.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepo extends JpaRepository<PostTag, Integer> {
}
