package com.example.lunchdecision.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchdecision.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    // You can define custom query methods if needed

}
