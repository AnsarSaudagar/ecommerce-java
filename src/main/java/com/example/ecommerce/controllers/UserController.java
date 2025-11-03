package com.example.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.AuthResponse;
import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.SignupRequest;
import com.example.ecommerce.dto.TokenValidationResponse;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.security.CustomUserDetailsService;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.util.JwtUtil;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignupRequest signupRequest) {
        try {
            User savedUser = userService.signUp(signupRequest);
            String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getId());
            
            // Remove password from response
            savedUser.setPassword(null);
            
            AuthResponse response = new AuthResponse(token, savedUser, "User registered successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, null, e.getMessage()));
        }
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.login(loginRequest);
            String token = jwtUtil.generateToken(user.getEmail(), user.getId());
            
            // Remove password from response
            user.setPassword(null);
            
            AuthResponse response = new AuthResponse(token, user, "Login successful");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, null, e.getMessage()));
        }
    }

    @PostMapping("validate-token")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.ok(new TokenValidationResponse(false, null, "No token provided"));
            }

            String token = authHeader.substring(7);
            
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.ok(new TokenValidationResponse(false, null, "Invalid or expired token"));
            }

            String email = jwtUtil.extractEmail(token);
            User user = userDetailsService.loadUserEntityByUsername(email);
            
            // Remove password from response
            user.setPassword(null);
            
            return ResponseEntity.ok(new TokenValidationResponse(true, user, "Token is valid"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.ok(new TokenValidationResponse(false, null, "User not found"));
        } catch (Exception e) {
            return ResponseEntity.ok(new TokenValidationResponse(false, null, "Token validation failed: " + e.getMessage()));
        }
    }
}
