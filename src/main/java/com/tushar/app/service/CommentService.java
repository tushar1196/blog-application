package com.tushar.app.service;

import com.tushar.app.model.Comment;
import com.tushar.app.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    public void saveComment(Comment comment) {
        if(comment.getCreatedAt()==null) {
            comment.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        }
        comment.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        commentRepo.save(comment);
        System.out.println("in comment service saveComment");
    }

    public List<Comment> findAllByPostId(int postId){
        return commentRepo.findAllByPostId(postId);
    }

    public void deleteById(int id) {
        commentRepo.deleteById(id);
    }

    public Comment findById(int id) {
        Comment comment = commentRepo.findById(id).get();
        return comment;
    }
}
