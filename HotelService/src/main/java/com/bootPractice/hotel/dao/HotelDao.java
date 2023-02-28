package com.bootPractice.hotel.dao;

import com.bootPractice.hotel.entities.Hotel;

import java.util.List;

public interface HotelDao {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel get(String id);
}
