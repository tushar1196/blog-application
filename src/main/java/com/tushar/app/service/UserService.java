package com.tushar.app.service;

import com.tushar.app.model.User;
import com.tushar.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService /*implements UserDetailsService*/ {

    @Autowired
    UserRepo userRepo;

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

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//        User user = userRepo.findByName(userName);
//        if(user==null) {
//            throw new UsernameNotFoundException("User Not Found");
//        }
//
//
//        return null;
//    }
}
