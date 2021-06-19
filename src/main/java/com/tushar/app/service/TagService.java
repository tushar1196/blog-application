package com.tushar.app.service;

import com.tushar.app.model.Tag;
import com.tushar.app.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class TagService {

    @Autowired
    TagRepo tagRepo;

    public List<Tag> findByPostId(int id) {
        return tagRepo.findByPostId(id);
    }

    public void saveTag(Tag tag) {
        tagRepo.save(tag);
    }

    public Tag findByName(String name) {
       return tagRepo.findByName(name);
    }

    public void saveTag(String tagsNames,int postId) {
        System.out.println();
        String[] tagNames = tagsNames.split(",");
        for (String tagName:tagNames) {
            if(findByName(tagName)==null) {
                Tag tag = new Tag();
                tag.setName(tagName);
                tag.setPostId(postId);
                tag.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                if (tag.getCreatedAt()==null)
                    tag.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
                saveTag(tag);
            }
        }
    }
}
