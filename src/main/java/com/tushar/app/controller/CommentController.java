package com.tushar.app.controller;

import com.tushar.app.model.Comment;
import com.tushar.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("/saveComment/{postId}")
    public String saveComment(@ModelAttribute("comment") Comment comment, @PathVariable int postId) {
        System.out.println("CommentController saveComment" + comment);
        commentService.saveComment(comment, postId);
        return "redirect:/read/?id=" + postId;
    }

    @RequestMapping("/saveComment/Update/{postId}")
    public String saveUpdateComment(@ModelAttribute("comment") Comment comment, @PathVariable int postId) {
        System.out.println("CommentController saveComment" + comment);
        commentService.saveUpdateComment(comment);
        return "redirect:/read/?id=" + postId;
    }

    @RequestMapping("/read/updateComment/{commentId}/{postId}")
    public String updateComment(@PathVariable("commentId") int commentId, @PathVariable("postId") int postId, Model model) {
        Comment comment = commentService.findById(commentId);
        model.addAttribute("comment", comment);
        model.addAttribute("postId", postId);
        return "updateComment";
    }

    @RequestMapping("/read/deleteComment/{commentId}/{postId}")
    public String delete(@PathVariable("commentId") int commentId , @PathVariable("postId") int postId ) {
        System.out.println("in comment controller before delete");
        commentService.deleteById(commentId);
        System.out.println("in comment controller after delete");
        return "redirect:/read/?id=" + postId;
    }
}
