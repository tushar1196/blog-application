package com.tushar.app.repository;

import com.tushar.app.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Integer> {

    public List<Tag> findByPostId(int id);
}
