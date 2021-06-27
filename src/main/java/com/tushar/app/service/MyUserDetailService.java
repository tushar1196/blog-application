package com.tushar.app.service;

import com.tushar.app.model.User;
import com.tushar.app.repository.UserRepository;
import com.tushar.app.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

//        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userName));
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }
        MyUserDetails myUserDetails = new MyUserDetails(user);

        return myUserDetails;
    }
}
