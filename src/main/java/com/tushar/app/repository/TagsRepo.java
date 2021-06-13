package com.tushar.app.repository;

import com.tushar.app.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagsRepo extends JpaRepository<Tags, Integer> {
}
