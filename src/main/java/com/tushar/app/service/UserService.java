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

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        return this.findByEmail(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(int userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
