package com.tushar.app.service;

import com.tushar.app.model.Post;
import com.tushar.app.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postsRepo;

    public void savePost(Post post) {
        if (post.getCreatedAt() == null) {
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
        return postsRepo.findById(id).get();
    }

    public Page<Post> findPaginatedPosts(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return postsRepo.findAll(pageable);
    }

    public List<Post> getPostSearch(String search) {
        return postsRepo.findAllByTitleContaining(search);
    }

}
