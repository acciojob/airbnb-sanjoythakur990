package com.driver.controllers;

import com.driver.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    HotelRepository hotelRepository=new HotelRepository();
    public String addHotel(Hotel hotel){
        return hotelRepository.addHotel(hotel);
    }
    public Integer addUser(User user){
        return hotelRepository.addUser(user);
    }
    public String getHotelWithMostFacilities(){
        return hotelRepository.getHotelWithMostFacilities();
    }
    public int bookARoom(Booking booking){
        return hotelRepository.bookARoom(booking);
    }
    public int getBookings(Integer aadharCard){
        return hotelRepository.getBookings(aadharCard);
    }
    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName){
        return hotelRepository.updateFacilities(newFacilities,hotelName);
    }
}
