package com.bootPractice.rating.repository;

import com.bootPractice.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {
    // *Important concept - implementing custom repository methods
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
