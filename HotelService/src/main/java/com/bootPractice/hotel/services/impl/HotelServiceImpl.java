package com.bootPractice.hotel.services.impl;

import com.bootPractice.hotel.dao.HotelDao;
import com.bootPractice.hotel.entities.Hotel;
import com.bootPractice.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelDao hotelDao;

    @Override
    public Hotel create(Hotel hotel) {
        String randomUserID = UUID.randomUUID().toString();
        hotel.setId(randomUserID);
        return hotelDao.create(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelDao.getAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelDao.get(id);
    }
}
