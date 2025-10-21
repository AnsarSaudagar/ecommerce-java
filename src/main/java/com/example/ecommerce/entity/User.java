package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("first_name")
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @JsonProperty("middle_name")
    @Column(name = "middle_name", length = 255)
    private String middleName;

    @JsonProperty("last_name")
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Getter
    @JsonProperty("email")
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @JsonProperty("email_verified_at")
    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @JsonProperty("password")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @JsonProperty("api_token")
    @Column(name = "api_token", length = 80)
    private String apiToken;

    @JsonProperty("remember_token")
    @Column(name = "remember_token", length = 100)
    private String rememberToken;

    @JsonProperty("created_at")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
