package com.tushar.app.service;

import com.tushar.app.model.Tag;
import com.tushar.app.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagRepo tagRepo;


//    public void saveTag(Tag tag) {
//        tagRepo.save(tag);
//    }
//
//    public Tag findByName(String name) {
//       return tagRepo.findByName(name);
//    }

}
