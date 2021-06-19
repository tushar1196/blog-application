package com.tushar.app.controller;

import com.tushar.app.model.Comment;
import com.tushar.app.model.Post;
import com.tushar.app.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommentController {

    @Resource
    CommentService commentService;

    @RequestMapping("/addComment/{postId}")
    public String addComment(Model model, @PathVariable int postId) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        model.addAttribute("comment", comment);
        return "addCommentForm";
    }

    @RequestMapping("/saveComment")
    public String saveComment(@ModelAttribute("comment") Comment comment) {
        System.out.println("CommentController saveComment"+comment);
        commentService.saveComment(comment);
        return "redirect:/read/?id="+comment.getPostId();
    }
    @RequestMapping("/read/updateComment")
    public String updateComment(int id,Model model) {
        Comment comment = commentService.findById(id);
        model.addAttribute(comment);
        return "addCommentForm";
    }
    @RequestMapping("/read/deleteComment")
    public String delete(int id) {
        System.out.println("in comment controller before delete");
        commentService.deleteById(id);
        System.out.println("in comment controller after delete");
        return "redirect:/dashboard";
    }
}
