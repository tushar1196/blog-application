package com.tushar.app.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.tushar.app.model.Post;
import com.tushar.app.model.Tag;
import com.tushar.app.service.PostService;
import com.tushar.app.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String viewBlogForm(Model model) {
        Post post = new Post();
        List<Tag> tags = tagService.getAllTags();
        model.addAttribute("post",post);
        model.addAttribute("tags",tags);
//        System.out.println(tags);
        return "addPost";
    }

    @GetMapping("/savepost")
    public String savePost(@ModelAttribute("post") Post post) {

        System.out.println("in post controoler savepost"+post);
        postService.savePost(post);
        return "redirect:/dashboard";
    }
    @RequestMapping("/delete")
    public String deletePost(int id) {
        postService.deletePostById(id);
        return "redirect:/dashboard";
    }
    @RequestMapping("/read")
    public String readPostById(int id,Model model) {
        Post post = postService.findPostById(id);
        model.addAttribute("post",post);
        return "readPost";
    }

    @RequestMapping("/update")
    public String updatePost(int id,Model model) {
        Post post = postService.findPostById(id);
        model.addAttribute("post",post);
        return "updatePost";
    }

    @RequestMapping("/saveEdit")
    public void saveEdit() {
        System.out.println("in saveEdit postcontroller");
    }
}
