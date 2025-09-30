package com.example.service;

import com.example.entity.Session;
import com.example.entity.User;
import com.example.repository.UserRepository;

public class UserService {


    public UserRepository userRepository;


    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User login(String email, String password)
    {
       User login =  this.userRepository.CheckEmailAndPassword(email,password);

       if(login != null)
       {
           Session.setUser(login);
           return login;

       }
       else
       {
           return null;
       }
    }
}
