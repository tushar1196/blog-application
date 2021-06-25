package com.tushar.app.service;

import com.tushar.app.model.Post;
import com.tushar.app.model.Tag;
import com.tushar.app.repository.PostRepository;
import com.tushar.app.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postsRepo;
    @Autowired
    TagRepository tagRepository;

    public void savePost(Post post, String helperTags) {
        String[] tagsNames = helperTags.split(",");
        for (String tagName : tagsNames) {
            if (tagRepository.findByName(tagName.toLowerCase().trim()) == null) {
                Tag tag = new Tag();
                tag.setName(tagName.toLowerCase().trim());
                tag.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                if (tag.getCreatedAt() == null)
                    tag.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
                post.getTags().add(tag);
                tag.getPosts().add(post);
            }
        }
        if (post.getCreatedAt() == null) {
            post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            post.setPublishedAt(Timestamp.valueOf(LocalDateTime.now()));
        }
        post.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        post.setPublished(true);
        postsRepo.save(post);
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

    public List<Post> findBySearchKeyword(String search) {
        return postsRepo.findAllBySearch(search);
    }

    public List<String> findDistinctAuthorNames() {
        System.out.println(postsRepo.finaDistinctAuthors());
        return postsRepo.finaDistinctAuthors();
    }

    public List<Post> findPostByFilter(List<Integer> id, String dateFrom, String dateTo) {
        Timestamp dateF;
        Timestamp dateT;
        if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
            dateF = Timestamp.from(Instant.parse(dateFrom + ":00.000Z"));
            dateT = Timestamp.from(Instant.parse(dateTo + ":00.000Z"));
            List<Post> posts;
            List<Post> filterPosts = new ArrayList<>();
            if (!id.isEmpty()) {
                posts = postsRepo.findAllByTagId(id);
                for (Post post : posts) {
                    if (post.getPublishedAt().compareTo(dateF) >= 0 && post.getPublishedAt().compareTo(dateT) <= 0) {
                        filterPosts.add(post);
                    }
                }
                return filterPosts;
            } else {
                posts = postsRepo.findAll();
                for (Post post : posts) {
                    if (post.getPublishedAt().compareTo(dateF) >= 0 && post.getPublishedAt().compareTo(dateT) <= 0) {
                        filterPosts.add(post);
                    }
                }
            }
            return filterPosts;
        } else return postsRepo.findAllByTagId(id);
    }
}




