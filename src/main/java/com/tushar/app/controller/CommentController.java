package com.tushar.app.controller;

import com.tushar.app.model.Comment;
import com.tushar.app.model.Post;
import com.tushar.app.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
//@RequestMapping("/read")
public class CommentController {

    @Resource
    CommentService commentService;

    @RequestMapping("/addComment")
    public String addComment(Model model, @ModelAttribute("post") Post post) {
        Comment comment = new Comment();
        comment.setPostId(post.getId());
        model.addAttribute("comment", comment);
        return "addCommentForm";
    }

    @PostMapping("/saveComment")
    public String saveComment(@ModelAttribute("comment") Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/addComment";
    }
}
