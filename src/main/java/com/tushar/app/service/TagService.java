package com.tushar.app.service;

import com.tushar.app.model.Tag;
import com.tushar.app.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagService {

    @Autowired
    TagRepo tagRepo;

    public List<Tag> getTagsByPost(int id) {
        System.out.println("in service tag");
        List<Tag> tags = tagRepo.findByPostId(id);
        System.out.println(tags);
        return tags;
    }


}
