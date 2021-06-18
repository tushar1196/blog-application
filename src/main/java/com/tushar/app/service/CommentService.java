package com.tushar.app.service;

import com.tushar.app.model.Comment;
import com.tushar.app.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    public void saveComment(Comment comment) {
        commentRepo.save(comment);
        System.out.println("in comment service saveComment");
    }






}
