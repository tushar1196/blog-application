package com.tushar.crud.repository;

import com.tushar.crud.model.PostTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagsRepo extends JpaRepository<PostTags, Integer> {
}
