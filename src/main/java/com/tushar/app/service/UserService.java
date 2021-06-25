package com.tushar.app.service;

import com.tushar.app.model.User;
import com.tushar.app.repository.UserRepo;
import com.tushar.app.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public boolean verifyCredential(String email, String password) {
        User user = userRepo.findByUserNameAndPassword(email, password);
        System.out.println(user);
        return user != null;
    }

    public String findById(int id) {
        User user = userRepo.findById(id);
        return user.getName();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if (user==null)
            throw new UsernameNotFoundException("UsernameNotFoundException");
        return new UserPrinciple(user);
    }
}
