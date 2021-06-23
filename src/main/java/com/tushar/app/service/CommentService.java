package com.tushar.app.service;

import com.tushar.app.model.Comment;
import com.tushar.app.model.Post;
import com.tushar.app.repository.CommentRepo;
import com.tushar.app.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    PostRepo postRepo;

    public void saveComment(Comment comment, int postId) {
        comment.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        comment.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        Post post = postRepo.findById(postId).get();
        post.getComments().add(comment);
        postRepo.save(post);
    }

    public void saveUpdateComment(Comment comment) {
        comment.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        commentRepo.save(comment);
    }

    public void deleteById(int id) {
        commentRepo.deleteById(id);
    }

    public Comment findById(int id) {
        return commentRepo.findById(id).get();
    }
}
