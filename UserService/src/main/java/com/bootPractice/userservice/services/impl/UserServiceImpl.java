package com.bootPractice.userservice.services.impl;

import com.bootPractice.userservice.dao.UserDao;
import com.bootPractice.userservice.entities.Hotel;
import com.bootPractice.userservice.entities.Rating;
import com.bootPractice.userservice.entities.User;
import com.bootPractice.userservice.external.services.HotelService;
import com.bootPractice.userservice.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserID = UUID.randomUUID().toString();
        user.setUserId(randomUserID);
        return userDao.saveUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUser(String userId) {
        User user = userDao.getUser(userId);
        Rating[] ratingOfUser = restTemplate.getForObject(
                "http://RATING-SERVICE/ratings/users/" + user.getUserId(),
                Rating[].class
        );
        List<Rating> ratings = Arrays.asList(ratingOfUser);
        List<Rating> ratingList = ratings.stream().map(
                rating -> {
                    ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(
                            "http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
                            Hotel.class);
                    Hotel hotel = hotelService.getHotel(rating.getHotelId());
                    logger.info("",forEntity.getBody());
                    rating.setHotel(hotel);

                    return rating;
                }
        ).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
