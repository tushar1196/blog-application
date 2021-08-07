package com.tushar.app.controller;

import com.tushar.app.model.User;
import com.tushar.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/login")
    public String userLogin(Authentication authentication) {
        if(authentication != null) {
            return "redirect:/";
        }
        return "userLogin";
    }

    @RequestMapping("/register")
    public String newUserRegister(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/saveuser")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_AUTHOR");
        userService.saveUser(user);
        String accountCreationMessage = "Thanks for creating Account";
        model.addAttribute("accountCreationMessage", accountCreationMessage);
        return "userLogin";
    }
}
