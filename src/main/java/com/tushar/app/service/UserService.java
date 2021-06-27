package com.tushar.app.service;

import com.tushar.app.model.User;
import com.tushar.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean verifyCredential(String email, String password) {
        User user = userRepository.findByUserName(email);
        System.out.println(user);
        return user != null;
    }

    public List<User> findAllUsers(String s) {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if( principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        User user = this.findByEmail(username);
        System.out.println(user+" ++++++++++++++++++++ "+username);
        return user;
    }
}
