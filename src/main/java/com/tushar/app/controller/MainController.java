package com.tushar.app.controller;

import com.tushar.app.model.Posts;
import com.tushar.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    CommentsRepo commentsRepo;
    @Autowired
    PostsRepo postsRepo;
    @Autowired
    PostTagsRepo postTagsRepo;
    @Autowired
    TagsRepo tagsRepo;
    @Autowired
    UserRepo userRepo;

    @RequestMapping("/blogsdashboard")
    public String getBlogs(Model model) {
        System.out.println("in blogs");
        List<Posts> posts = postsRepo.findAll();
        System.out.println(posts);
//        model.addAttribute("posts",posts);
        return "blogsdashboard";
    }












}
