package com.tushar.app.controller;

import com.tushar.app.model.User;
import com.tushar.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        System.out.println(user);
        userService.saveUser(user);
        String accountCreationMessage = "Thanks for creating Account";
        model.addAttribute("accountCreationMessage", accountCreationMessage);
        return "userLogin";
    }

    @RequestMapping("/checkCredentials")
    public String verifyCredentials(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isValidUser = userService.verifyCredential(email, password);
        if (isValidUser) {
            return "redirect:/dashboard";
        } else {
            String invalidCredentialMessage = "Invalid Credentials ";
            model.addAttribute("invalidCredentialMessage", invalidCredentialMessage);
            return "userLogin";
        }
    }
}
