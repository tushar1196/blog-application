package com.tushar.app.service;

import com.tushar.app.model.Post;
import com.tushar.app.model.Tag;
import com.tushar.app.repository.PostRepo;
import com.tushar.app.repository.TagRepo;
import com.tushar.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepo postsRepo;




    public List<Post> getPosts (){
        List<Post> posts= postsRepo.findAll();
        System.out.println("in service class");
        return posts;
    }

    public void savePost(Post post) {
        if(post.getCreatedAt()==null) {
            post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            post.setPublishedAt(Timestamp.valueOf(LocalDateTime.now()));
        }
        post.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        post.setPublished(true);
        postsRepo.save(post);
        System.out.println("in post service savepost");
    }


    public void deletePostById(int id) {
        postsRepo.deleteById(id);
    }

    public Post findPostById(int id) {
        Post post = postsRepo.findById(id).get();
        return post;
    }

}
