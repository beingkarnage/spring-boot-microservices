package com.bootPractice.userservice.dao;

import com.bootPractice.userservice.entities.User;

import java.util.List;

public interface UserDao {
    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String userId);
    // TODO : delete, update
}
