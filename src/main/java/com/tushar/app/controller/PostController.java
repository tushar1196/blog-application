package com.tushar.app.controller;

import com.tushar.app.model.Comment;
import com.tushar.app.model.Post;
import com.tushar.app.model.Tag;
import com.tushar.app.service.CommentService;
import com.tushar.app.service.PostService;
import com.tushar.app.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

@Controller
public class PostController {

    @Resource
    PostService postService;
    @Resource
    TagService tagService;
    @Resource
    CommentService commentService;


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

        model.addAttribute("posts", listPosts);
        return "dashboard";
    }

    @RequestMapping("/addpost")
    public String viewForm(Model model) {
        return viewBlogForm(model);
    }

    @RequestMapping("/page/addpost")
    public String viewBlogForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "addPost";
    }

    @RequestMapping("/savepost")
    public String savePost(@RequestParam("helperTags") String helperTags, @ModelAttribute("post") Post post) {
        System.out.println("in post controoler savepost tags are" + helperTags);
        System.out.println("in post controoler savepost" + post);
        postService.savePost(post);
        tagService.saveTag(helperTags,post.getId());
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
        post.setComments(commentService.findAllByPostId(id));
        post.setTags(tagService.findByPostId(id));
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
        List<Post> posts = postService.getPostSearch(search);
        model.addAttribute("posts", posts);
        return "dashboard";
    }
}
