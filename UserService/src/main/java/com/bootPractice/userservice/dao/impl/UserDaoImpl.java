package com.bootPractice.userservice.dao.impl;

import com.bootPractice.userservice.dao.UserDao;
import com.bootPractice.userservice.entities.User;
import com.bootPractice.userservice.exceptions.ResourceNotFoundException;
import com.bootPractice.userservice.repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given id is not found " + userId));
    }
}
