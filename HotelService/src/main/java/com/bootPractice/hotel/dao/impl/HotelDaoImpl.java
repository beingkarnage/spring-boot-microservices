package com.bootPractice.hotel.dao.impl;

import com.bootPractice.hotel.dao.HotelDao;
import com.bootPractice.hotel.entities.Hotel;
import com.bootPractice.hotel.exceptions.ResourceNotFoundException;
import com.bootPractice.hotel.repositries.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("hotelDao")
public class HotelDaoImpl implements HotelDao {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("hotel with given id is not found " + id));
    }
}
