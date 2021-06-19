package com.tushar.app.controller;

import com.tushar.app.model.Tag;
import com.tushar.app.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class TagController {

    @Resource
    TagService tagService;


    @RequestMapping("/addTag/{postId}")
    public String addTags(Model model, @PathVariable("postId") int postId) {
        Tag tag = new Tag();
        tag.setPostId(postId);
        model.addAttribute("tag",tag);
        return "addTag";
    }

}
