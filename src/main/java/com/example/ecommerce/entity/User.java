package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "middle_name", length = 255)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

//    @Column(name = "two_factor_secret", columnDefinition = "TEXT")
//    private String twoFactorSecret;
//
//    @Column(name = "two_factor_recovery_codes", columnDefinition = "TEXT")
//    private String twoFactorRecoveryCodes;
//
//    @Column(name = "two_factor_confirmed_at")
//    private LocalDateTime twoFactorConfirmedAt;

    @Column(name = "api_token", length = 80)
    private String apiToken;

    @Column(name = "remember_token", length = 100)
    private String rememberToken;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
