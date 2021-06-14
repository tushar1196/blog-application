package com.tushar.app.controller;

import com.tushar.app.model.Post;
import com.tushar.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    Post post;

    @RequestMapping("/dashboard")
    public String getBlogs(Model model) {
        System.out.println("in get posts");
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts",posts);
        return "dashboard";
    }

    @RequestMapping("/postAdd")
    public String goToBlogForm(Model model) {
        model.addAttribute("post",post);
        return "postAdd";
    }












}
