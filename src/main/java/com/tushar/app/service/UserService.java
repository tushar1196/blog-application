package com.tushar.app.service;

import com.tushar.app.model.User;
import com.tushar.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String findById(int id) {
        User user = userRepository.findById(id);
        return user.getName();
    }
}
