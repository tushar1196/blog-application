package com.tushar.app.repository;

import com.tushar.app.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepo extends JpaRepository<Tag, Integer> {

}
