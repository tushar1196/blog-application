package com.tushar.app.controller;

import com.tushar.app.model.Post;
import com.tushar.app.model.Tag;
import com.tushar.app.service.PostService;
import com.tushar.app.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PostController {

    @Resource
    PostService postService;
    @Resource
    TagService tagService;


    @RequestMapping("/dashboard")
    public String getBlogs(Model model) {
        System.out.println("in get posts");

        List<Post> posts = postService.getPosts();
        model.addAttribute("posts",posts);
        return "dashboard";
    }

    @RequestMapping("/addpost")
    public ModelAndView goToBlogForm(Model model) {
        Post post = new Post();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addPost");
        modelAndView.addObject("post",post);


        List<Tag> tags = tagService.getTags();
        modelAndView.addObject("tags",tags);
        return modelAndView;
    }

    @GetMapping("/savepost")
    public String savePost(@RequestParam String title , @RequestParam String excerpt,@RequestParam String content,@RequestParam String author) {
        System.out.println(title+ " "+excerpt+" "+content+" "+author);
        Post post = new Post();
        post.setTitle(title);
        post.setExcerpt(excerpt);
        post.setContent(content);
        post.setAuthor(author);
        post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        postService.savePost(post);
        return "addPost";
    }












}
