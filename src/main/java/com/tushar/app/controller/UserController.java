package com.tushar.app.controller;

import com.tushar.app.model.User;
import com.tushar.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public String userLogin() {
        return "userLogin";
    }

    @RequestMapping("/register")
    public String newUserRegister(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "addUser";
    }

    @GetMapping("/saveUser")
    public String saveUser(@ModelAttribute ("user") User user, Map<String, Object> model) {
        System.out.println(user);
        userService.saveUser(user);
        return null;
    }







    @RequestMapping("/checkCredentials")
    public String verifyCredentials (@RequestParam String email , @RequestParam String password) {
        boolean isValidUser = userService.verifyCredientials(email, password);
        if (isValidUser)
            return "/dashboard";
        else
            return "userLogin";
    }
    @RequestMapping("/saveUser")
    public String saveUser(@RequestParam String name,@RequestParam String email,@RequestParam String password,@RequestParam String confirmPassword) {
        System.out.println("in save user controller");


        return null;
    }
}
