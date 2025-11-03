package com.example.ecommerce.service;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.SignupRequest;
import com.example.ecommerce.entity.User;

public interface UserService {
    User signUp(SignupRequest signupRequest) throws Exception;
    User login(LoginRequest loginRequest) throws Exception;
    User getUserById(Long userId);
}
