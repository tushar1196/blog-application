package com.tushar.app.api;

import com.tushar.app.model.*;
import com.tushar.app.service.CommentService;
import com.tushar.app.service.PostService;
import com.tushar.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MainApi {

    @Autowired
    private PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    private UserService userService;


    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/posts/{postId}")
    public Post getPost(@PathVariable("postId") int postId) {
        return postService.findPostById(postId);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post, @RequestParam("helperTags") String helperTags) {
        postService.savePost(post, helperTags);
        return post;
    }

    @DeleteMapping("/posts/{postId}")
    public int deletePost(@PathVariable("postId") int postId) {
        postService.deletePostById(postId);
        return postId;
    }

    @PutMapping("/posts")
    public Post updatePost(@RequestBody Post post, @RequestParam("helperTags") String helperTags) {
        postService.savePost(post, helperTags);
        return post;
    }

    @GetMapping("/comments")
    public List<Comment> getComments() {
        return commentService.findAllComment();
    }

    @GetMapping("/comments/{commentId}")
    public Comment getComment(@PathVariable("commentId") int commentId) {
        return commentService.findById(commentId);
    }

    @PostMapping("/comments")
    public Comment addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return comment;
    }

    @DeleteMapping("/comments/{commentId}")
    public int deleteComment(@PathVariable("commentId") int commentId) {
        commentService.deleteById(commentId);
        return commentId;
    }

    @PutMapping("/comments")
    public Comment updateComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return comment;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
        return userService.findById(userId);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public int deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return userId;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/filter")
    public List<Post> getPostsByFilter(@RequestBody Filter filter, @RequestParam("dateFrom") String dateFrom,
                                       @RequestParam("dateTo") String dateTo) {
        List<Integer> id = new ArrayList<>();
        for (Tag tag : filter.getTags()) {
            System.out.println(tag.getName());
            id.add((tag.getId()));
        }
        List<String> authors = new ArrayList<>(filter.getAuthors());
        return postService.findPostByFilter(id, authors, dateFrom, dateTo);
    }

    @GetMapping("/paginated/{pageNo}")
    public List<Post> viewPostPaginated(@PathVariable("pageNo") int pageNo, @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDirection") String sortDirection) {
        int pageSize = 5;
        Page<Post> page = postService.findPaginatedPosts(pageNo, pageSize, sortField, sortDirection);
        return page.getContent();
    }

    @GetMapping("/search")
    public List<Post> getPostBySearch(@RequestParam("search") String search) {
        return postService.findBySearchKeyword(search);
    }

    @PostMapping("/author/posts")
    public Post savePost(@RequestParam("tag") String tags, @RequestBody Post post, Authentication authentication) {
        String[] tagsArray = tags.split(",");
        List<String> tagsList = Arrays.asList(tagsArray);
        User user = userService.findByEmail(authentication.getName());
        post.setAuthor(user.getName());
        postService.savePost(post, tags);
        return post;
    }

    @PutMapping("/author/posts/{id}")
    public Post updatePost(@PathVariable(value = "id") Integer id, @RequestBody Post post,
                           Authentication authentication) {
        Post posts = postService.findPostById(id);
        User user = userService.findByEmail(authentication.getName());
        System.out.println(post);
        Timestamp instant = Timestamp.from(Instant.now());

        if (posts.getAuthor().equals(user.getName()) || user.getRole().equals("ROLE_ADMIN")) {
            posts.setAuthor(user.getName());
            posts.setUpdatedAt(instant);
            posts.setTitle(post.getTitle());
            posts.setContent(post.getContent());
            posts.setExcerpt(post.getExcerpt());
            postService.savePost(posts);
            return post;
        }
        return post;
    }

    @DeleteMapping("author/posts/{id}")
    public void deletePost(@PathVariable(value = "id") Integer id, Authentication authentication) {
        Post post = postService.findPostById(id);
        User user = userService.findByEmail(authentication.getName());
        if (post.getAuthor().equals(user.getName()) || user.getRole().equals("ROLE_ADMIN")) {
            postService.deletePostById(id);
        }
    }
}
