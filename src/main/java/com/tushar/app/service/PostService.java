package com.tushar.app.service;

import com.tushar.app.model.Post;
import com.tushar.app.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Post save = postsRepo.save(post);
        System.out.println(save);
    }
}
