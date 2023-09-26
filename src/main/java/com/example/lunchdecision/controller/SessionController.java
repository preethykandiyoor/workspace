package com.example.lunchdecision.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lunchdecision.model.Session;
import com.example.lunchdecision.model.User;
import com.example.lunchdecision.repository.SessionRepository;
import com.example.lunchdecision.service.SessionService;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSession(@RequestBody User initiator) {
        Session createdSession = sessionService.createSession(initiator);
        return ResponseEntity.ok("Session created with ID: " + createdSession.getId());
    }

    @PostMapping("/{sessionId}/join")
    public ResponseEntity<String> joinSession(@PathVariable Long sessionId, @RequestBody User user) {
        boolean success = sessionService.joinSession(sessionId, user);
        if (success) {
            return ResponseEntity.ok("User " + user.getUsername() + " joined session " + sessionId);
        } else {
            return ResponseEntity.badRequest().body("Session " + sessionId + " not found or already ended.");
        }
    }

    @PostMapping("/{sessionId}/end")
    public ResponseEntity<String> endSession(@PathVariable Long sessionId, @RequestHeader Long userId) {
        Session session = sessionService.getSession(sessionId);

        if (session == null) {
            return ResponseEntity.badRequest().body("Session " + sessionId + " not found.");
        }

        if (session.getInitiator().getId() != userId ) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only the user who initiated the session can end it.");
        }

        Session endedSession = sessionService.endSession(sessionId);

        if (endedSession != null) {
            return ResponseEntity.ok("Session " + sessionId + " ended.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to end session.");
        }
    }
 
}
