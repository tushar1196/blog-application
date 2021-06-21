package com.tushar.app.repository;

import com.tushar.app.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<Tag, Integer> {

    public Tag findByName(String name);
}
