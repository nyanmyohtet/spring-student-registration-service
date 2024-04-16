package com.nyanmyohtet.studentregistrationservice.api.rest;

import com.nyanmyohtet.studentregistrationservice.api.request.AuthRequest;
import com.nyanmyohtet.studentregistrationservice.api.response.AuthResponse;
import com.nyanmyohtet.studentregistrationservice.persistence.model.User;
import com.nyanmyohtet.studentregistrationservice.util.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthRestController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    private static final Logger logger = LogManager.getLogger(AuthRestController.class);

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            // Authenticate user
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            // Extract user details from authentication
            User user = (User) authentication.getPrincipal();

            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            logger.error(ex.getMessage());
            // TODO: thrown custom ex
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
