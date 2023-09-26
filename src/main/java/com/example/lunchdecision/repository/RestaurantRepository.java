package com.example.lunchdecision.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchdecision.model.Restaurant;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
	 Optional<Restaurant> findById(Long id);
    List<Restaurant> findByName(String name);
  
}
