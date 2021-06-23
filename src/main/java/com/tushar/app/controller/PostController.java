package com.tushar.app.controller;

import com.tushar.app.model.Comment;
import com.tushar.app.model.Filter;
import com.tushar.app.model.Post;
import com.tushar.app.model.Tag;
import com.tushar.app.service.PostService;
import com.tushar.app.service.TagService;
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

    @RequestMapping("/dashboard")
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

        model.addAttribute("posts", listPosts);
        return "dashboard";
    }

    @RequestMapping("/addPost")
    public String viewForm(Model model) {
        return viewBlogForm(model);
    }

    @RequestMapping("/page/addPost")
    public String viewBlogForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "addPost";
    }

    @RequestMapping("/savePost")
    public String savePost(@RequestParam("helperTags") String helperTags, @ModelAttribute("post") Post post) {
        postService.savePost(post, helperTags);
        return "redirect:/dashboard";
    }

    @RequestMapping("/delete")
    public String deletePost(int id) {
        postService.deletePostById(id);
        return "redirect:/dashboard";
    }

    @RequestMapping("/read")
    public String readPostById(int id, Model model) {
        Post post = postService.findPostById(id);
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        model.addAttribute("postId", id);
        model.addAttribute("post", post);
        return "readPost";
    }

    @RequestMapping("/update")
    public String updatePost(@RequestParam("id") int id, Model model) {
        Post post = postService.findPostById(id);
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
        System.out.println("in filter");
        System.out.println("dateFrom= " + dateFrom + "dateTo= " + dateTo);
        List<Integer> id = new ArrayList<>();
        for (Tag tag : filter.getTags()) {
            System.out.println(tag.getName());
            id.add((tag.getId()));
        }
        model.addAttribute("posts", postService.findPostByFilter(id, dateFrom, dateTo));
        return "dashboard";
    }
}