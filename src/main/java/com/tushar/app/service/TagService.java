package com.tushar.app.service;

import com.tushar.app.model.Tag;
import com.tushar.app.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagService {

    @Autowired
    TagRepo tagRepo;

    public List<Tag> getTags() {
        System.out.println("in service tag");
        List<Tag> tags = tagRepo.findAll();
        return tags;
    }
}
