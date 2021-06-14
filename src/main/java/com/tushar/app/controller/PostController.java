package com.tushar.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {

    @RequestMapping("/blogsdashboard")
    public String getBlogs(Model model) {
        System.out.println("in blogs");


//        model.addAttribute("posts",posts);
        return "blogsdashboard";
    }












}
