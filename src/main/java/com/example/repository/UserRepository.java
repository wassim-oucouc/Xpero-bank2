package com.example.repository;

import com.example.entity.User;

public interface UserRepository {


    public User CheckEmailAndPassword(String email, String password);
}
