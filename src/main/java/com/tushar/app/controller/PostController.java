package com.tushar.app.controller;

import com.tushar.app.model.*;
import com.tushar.app.service.PostService;
import com.tushar.app.service.TagService;
import com.tushar.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String getBlogs(Model model) {
        return viewPostPaginated(1, model, "title", "asc");
    }

    @RequestMapping("/page/{pageNo}")
    public String viewPostPaginated(@PathVariable("pageNo") int pageNo, Model model, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection) {
        int pageSize = 5;

        Page<Post> page = postService.findPaginatedPosts(pageNo, pageSize, sortField, sortDirection);
        List<Post> listPosts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        Filter filter = new Filter();
        List<Tag> tags = tagService.findAllTags();
        model.addAttribute("tags", tags);
        model.addAttribute("filter", filter);
        List<String> authors = postService.findDistinctAuthorNames();
        model.addAttribute("authors", authors);

        User user = userService.getLoggedUser();
        if (user != null) {
            model.addAttribute("helperName", user.getName());
        } else {
            model.addAttribute("helperName", "null");
        }
        model.addAttribute("posts", listPosts);
//        System.out.println(user.getRole()+" ____________ " );
        if (user != null && user.getRole().equals("ROLE_ADMIN")) {
            System.out.println(user+"+++++++++++++++++++++++++++++++++++++++++");
            return "admindashboard";
        } else {
            return "dashboard";
        }
    }

    @RequestMapping("/addpost")
    public String viewForm(Model model) {
        return viewBlogForm(model);
    }

    @RequestMapping("/page/addpost")
    public String viewBlogForm(Model model) {
        Post post = new Post();
        User user = userService.getLoggedUser();
        System.out.println(user+"  in post controller ?????????????????????????????????????????????");
        post.setAuthor(user.getName());
        model.addAttribute("role", user.getRole());
        model.addAttribute("post", post);
        System.out.println("above return of add post");
        return "addPost";
    }

    @RequestMapping("/savepost")
    public String savePost(@RequestParam("helperTags") String helperTags, @ModelAttribute("post") Post post) {
        postService.savePost(post, helperTags);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String deletePost(int id) {
        postService.deletePostById(id);
        return "redirect:/";
    }

    @RequestMapping("/read")
    public String readPostById(int id, Model model) {
        Post post = postService.findPostById(id);
        User user = userService.getLoggedUser();
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        if(user!=null) {
            model.addAttribute("userName",user.getName());
            comment.setName(user.getName());
            comment.setEmail(user.getEmail());
        } else {
            model.addAttribute("userName","null");
        }
        model.addAttribute("postId", id);
        model.addAttribute("post", post);
//        System.out.println("role_________________++++++++++++++ "+user.getRole());
        if (user!=null && user.getRole().equals("ROLE_AUTHOR")) {
            return "readPostAuthorisedUser";
        } else if (user!=null && user.getRole().equals("ROLE_ADMIN")) {
            return "readPostAdmin";
        } else {
            return "readPost";
        }
    }

    @RequestMapping("/update")
    public String updatePost(@RequestParam("id") int id, Model model) {
        Post post = postService.findPostById(id);
        User user = userService.getLoggedUser();
        System.out.println(user+"  in post controller ?????????????????????????????????????????????");
        post.setAuthor(user.getName());
        model.addAttribute("role", user.getRole());
        model.addAttribute("post", post);
        return "addPost";
    }

    @RequestMapping("/search")
    public String getPostBySearch(@RequestParam("search") String search, Model model) {
        List<Post> posts = postService.findBySearchKeyword(search);
        model.addAttribute("posts", posts);
        return "dashboard";
    }

    @RequestMapping("/filter")
    public String getPostsByFilter(@ModelAttribute("filter") Filter filter, @RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo, Model model) {
        List<Integer> id = new ArrayList<>();
        for (Tag tag : filter.getTags()) {
            System.out.println(tag.getName());
            id.add((tag.getId()));
        }
        model.addAttribute("posts", postService.findPostByFilter(id, dateFrom, dateTo));
        return "dashboard";
    }
}