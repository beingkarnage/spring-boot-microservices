package com.bootPractice.userservice.controller;

import com.bootPractice.userservice.entities.User;
import com.bootPractice.userservice.services.UserService;
import com.bootPractice.userservice.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    int retryCount=1;
    @GetMapping("/{userId}")// should be same as parameter
    @RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback") // name should be same as resilience4j config
//    @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//        logger.info("Rating-Hotel-Breaker, Fallback executed [Rating Service might be down]",ex.getMessage());
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        User user = User.builder().email("dummy@mail.com").name("dummy")
                .about("dummy user created due to some service is down").userId("10001").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUser();

        return ResponseEntity.ok(users);
    }

}
