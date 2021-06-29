package com.tushar.app.service;

import com.tushar.app.model.Comment;
import com.tushar.app.model.Post;
import com.tushar.app.repository.CommentRepository;
import com.tushar.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public void saveComment(Comment comment, int postId) {
        comment.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        comment.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        Post post = postRepository.findById(postId).get();
        post.getComments().add(comment);
        postRepository.save(post);
    }

    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    public void saveUpdateComment(Comment comment) {
        comment.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        commentRepository.save(comment);
    }

    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }

    public Comment findById(int id) {
        return commentRepository.findById(id).get();
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
