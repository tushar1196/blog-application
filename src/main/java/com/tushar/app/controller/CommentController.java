package com.tushar.app.controller;

import com.tushar.app.service.CommentService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
//@RequestMapping("/read")
public class CommentController {

    @Resource
    CommentService commentService;
}
