package com.example.lunchdecision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchdecision.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // You can define custom query methods if needed
}

