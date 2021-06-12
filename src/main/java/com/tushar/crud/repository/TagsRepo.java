package com.tushar.crud.repository;

import com.tushar.crud.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagsRepo extends JpaRepository<Tags, Integer> {
}
