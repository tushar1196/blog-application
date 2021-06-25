package com.tushar.app.service;

import com.tushar.app.model.Tag;
import com.tushar.app.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }


}
