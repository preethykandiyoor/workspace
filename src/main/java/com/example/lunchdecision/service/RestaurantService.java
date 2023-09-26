package com.example.lunchdecision.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lunchdecision.model.Restaurant;
import com.example.lunchdecision.model.Session;
import com.example.lunchdecision.model.User;
import com.example.lunchdecision.repository.RestaurantRepository;
import com.example.lunchdecision.repository.SessionRepository;

import org.springframework.util.StringUtils;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
   
   
   
    public Restaurant submitRestaurant(Long sessionId, Restaurant restaurant) {
        // Validate restaurant data
        if (!isValidRestaurant(restaurant)) {
            throw new IllegalArgumentException("Invalid restaurant data");
        }

        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (!optionalSession.isPresent()) {
            throw new IllegalArgumentException("Session does not exist");
        }

        Session session = optionalSession.get();
        if (!session.isActive()) {
            throw new IllegalArgumentException("Session is not active");
        }
        ArrayList<Session> sessionList= new ArrayList();
        sessionList.add(session);
        restaurant.setSessions(sessionList);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        session.getRestaurants().add(savedRestaurant);
        sessionRepository.save(session); // Update the session

        return savedRestaurant;
    }

    public static boolean isValidRestaurant(Restaurant restaurant) {
        if (restaurant.getName() == null || restaurant.getName().isEmpty()) {
            return false;
        }

        if (restaurant.getName().length() > 255) {
            return false;
        }

        if (restaurant.getId() == null || restaurant.getId() <= 0) {
            return false;
        }

        if (restaurant.getCuisine() == null || restaurant.getCuisine().isEmpty()) {
            return false;
        }

        if (restaurant.getAddress() == null || restaurant.getAddress().isEmpty()) {
            return false;
        }

        // Validate the cuisine type

        // Validate the address

        return true;
    }
    public List<Restaurant> getRestaurants(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (!optionalSession.isPresent()) {
            return Collections.emptyList();
        }

        Session session = optionalSession.get();
       return session.getRestaurants();
    }
    
    public Restaurant createRestaurant(Restaurant restaurant) {
    	 if (!isValidRestaurant(restaurant)) {
             throw new IllegalArgumentException("Invalid restaurant data");
         }
        return restaurantRepository.save(restaurant);
    }

    
}


