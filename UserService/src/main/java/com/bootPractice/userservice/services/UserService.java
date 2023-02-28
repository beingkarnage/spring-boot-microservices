package com.bootPractice.userservice.services;

import com.bootPractice.userservice.entities.User;

import java.util.List;


public interface UserService {
    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String userId);
}
