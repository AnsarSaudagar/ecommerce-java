package com.example.ecommerce.controllers;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody User user)  {
        try{
            User savedUser = this.userService.signUp(user);
            return ResponseEntity.ok(savedUser);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
