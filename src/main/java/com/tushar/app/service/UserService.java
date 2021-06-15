package com.tushar.app.service;

import com.tushar.app.model.User;
import com.tushar.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public void saveUser(User user) {
        userRepo.save(user);
        System.out.println("user repo save user");
    }






    public boolean verifyCredientials(String email,String password) {
        List<User> user = userRepo.findByUserNameAndPassword(email, password);
        System.out.println(user);
        if(user.size()>0)
            return true;
        else
            return false;
    }
}
