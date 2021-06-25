package com.tushar.app.service;

import com.tushar.app.model.User;
import com.tushar.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService /*implements UserDetailsService*/ {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean verifyCredential(String email, String password) {
        User user = userRepository.findByUserNameAndPassword(email, password);
        System.out.println(user);
        return user != null;
    }

    public String findById(int id) {
        User user = userRepository.findById(id);
        return user.getName();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByName(username);
//        if (user==null)
//            throw new UsernameNotFoundException("UsernameNotFoundException");
//        return new UserPrinciple(user);
//    }
}
