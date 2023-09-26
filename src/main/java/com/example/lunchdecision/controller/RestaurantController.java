package com.example.lunchdecision.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lunchdecision.service.RestaurantService;
import com.example.lunchdecision.service.SessionService;
import com.example.lunchdecision.model.Restaurant;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
    @Autowired
    private  RestaurantService restaurantService;
    @Autowired
    private  SessionService sessionService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

   
    
    @PostMapping("/sessions/{sessionId}/submit")
    public void submitRestaurant(@PathVariable Long sessionId, @RequestBody Restaurant restaurant) {
    	restaurantService.createRestaurant(restaurant);
    	sessionService.submitRestaurant(sessionId, restaurant);
    }

    @GetMapping("/sessions/{sessionId}/get")
    public List<Restaurant> getRestaurants(@PathVariable Long sessionId) {
        return restaurantService.getRestaurants(sessionId);
    }
    
    @GetMapping("/sessions/{sessionId}/pickrestaurant")
    public Restaurant pickRandomRestaurant(@PathVariable Long sessionId) {
        List<Restaurant> restaurants = restaurantService.getRestaurants(sessionId);
        Random random = new Random();
        int randomIndex = random.nextInt(restaurants.size());
        Restaurant pickedRestaurant = restaurants.get(randomIndex);
        return pickedRestaurant;
    }
}
