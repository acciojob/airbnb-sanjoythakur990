package com.driver.controllers;
import com.driver.model.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HotelRepository {
    Map<String,Hotel> hotelDb=new HashMap<>();
    Map<Integer,User> userDb=new HashMap<>();
    Map<Integer, List<Booking>> bookingMap = new HashMap<>();
    public String addHotel(Hotel hotel){
        String name=hotel.getHotelName();
        if(hotelDb.containsKey(name) || hotel==null)
            return "FAILURE";
        hotelDb.put(name,hotel);
        return "SUCCESS";
    }
    public Integer addUser(User user){
        int no=user.getaadharCardNo();
        userDb.put(no,user);
        return no;
    }
    public String getHotelWithMostFacilities(){
        int count = 0;
        for(Hotel hotel : hotelDb.values()){
            count = Math.max(hotel.getFacilities().size() , count);
        }
        if(count == 0)
            return "";
        Set<String> list = new TreeSet<>();
        for(Hotel hotel : hotelDb.values()){
            if(hotel.getFacilities().size() == count){
                list.add(hotel.getHotelName());
            }
        }
        for(String s : list){
            return s;
        }
        return "";
    }
    public int bookARoom(Booking booking){
        if(hotelDb.containsKey(booking.getHotelName())){
            Hotel hotel = hotelDb.get(booking.getHotelName());
            if(hotel.getAvailableRooms() >= booking.getNoOfRooms()){
                booking.setBookingId(String.valueOf(UUID.randomUUID()));
                booking.setAmountToBePaid(hotel.getPricePerNight() * booking.getNoOfRooms());
                hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getNoOfRooms());
                hotelDb.put(hotel.getHotelName() , hotel);
                if(bookingMap.containsKey(booking.getBookingAadharCard())){
                    List<Booking> bookingList = bookingMap.get(booking.getBookingAadharCard());
                    bookingList.add(booking);
                    bookingMap.put(booking.getBookingAadharCard(),bookingList);
                } else{
                    List<Booking> bookingList = new ArrayList<>();
                    bookingList.add(booking);
                    bookingMap.put(booking.getBookingAadharCard(),bookingList);
                }
                return booking.getAmountToBePaid();
            } else {
                return -1;
            }
        }
        return -1;
    }
    public int getBookings(Integer aadharCard){
        if(bookingMap.containsKey(aadharCard))
            return bookingMap.get(aadharCard).size();
        else
            return 0;
    }
    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName){
        if(hotelDb.containsKey(hotelName)){
            Hotel hotel = hotelDb.get(hotelName);
            List<Facility> list = hotel.getFacilities();
            for(Facility facility : newFacilities){
                if(!list.contains(facility))
                    list.add(facility);
            }
            hotelDb.put(hotelName,hotel);
            return hotel;
        }
        return null;
    }
}
