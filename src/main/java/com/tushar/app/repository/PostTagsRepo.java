package com.tushar.app.repository;

import com.tushar.app.model.PostTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagsRepo extends JpaRepository<PostTags, Integer> {
}
