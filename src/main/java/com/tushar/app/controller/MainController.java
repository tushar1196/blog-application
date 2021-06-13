package com.tushar.app.controller;

import com.tushar.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/blogssdashboard")
    public String getBlogs() {
        System.out.println("in blogs");
        


        return "blogdashboard";
    }












}
