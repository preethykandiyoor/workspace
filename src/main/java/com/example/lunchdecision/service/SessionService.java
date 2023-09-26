package com.example.lunchdecision.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lunchdecision.model.Restaurant;
import com.example.lunchdecision.model.Session;
import com.example.lunchdecision.model.User;
import com.example.lunchdecision.repository.SessionRepository;



@Service
public class SessionService {
	private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session createSession(User initiator) {
        // Create a new session and set the initiator
     //   Session newSession = new Session(UUID.randomUUID().toString(), initiator);
    	 Session newSession = new Session( initiator);
        return sessionRepository.save(newSession);
    }

    public boolean joinSession(Long sessionId, User user) {
    	// Retrieve the session from the repository
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);

        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            
            if (session.isActive()) {
                // Check if the user is already in the session to avoid duplicates
                boolean userAlreadyInSession = session.getUsers().stream()
                        .anyMatch(u -> u.getId().equals(user.getId()));
                
                if (!userAlreadyInSession) {
                	System.out.println("Test line"+session.getId());
                    session.getUsers().add(user);
                    sessionRepository.save(session); // Update the session
                    return true;
                }
            }
        }

        return false;
    }
    public boolean submitRestaurant(Long sessionId, Restaurant rest) {
    	// Retrieve the session from the repository
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);

        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            
            if (session.isActive()) {
                    
                	System.out.println("Test line"+session.getId());
                    session.getRestaurants().add(rest);
                    sessionRepository.save(session); // Update the session
                    return true;
                
            }
        }

        return false;
    }
    public Session endSession(Long sessionId) {
    	// Retrieve the session from the repository
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);

        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            
            if (session.isActive()) {
                session.endSession();
                sessionRepository.save(session); // Update the session
                return session;
            }
        }

        return null;
    }
    
    public Session getSession(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (!optionalSession.isPresent()) {
            return null;
        }

        return optionalSession.get();
    }
}

