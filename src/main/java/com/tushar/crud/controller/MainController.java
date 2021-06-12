package com.tushar.crud.controller;

import com.tushar.crud.repository.*;
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

    @RequestMapping("/home")
    public String home() {
        return "home.html";
    }












}
