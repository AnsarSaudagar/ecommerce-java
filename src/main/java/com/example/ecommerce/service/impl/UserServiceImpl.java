package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public User signUp(User user) throws Exception {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new Exception("Email already Registered");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        return user;
        return userRepository.save(user);
    }
}
