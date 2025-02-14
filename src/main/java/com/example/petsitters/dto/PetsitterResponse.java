package com.example.petsitters.dto;

import java.time.LocalDateTime;

import lombok.Getter;   
import lombok.Setter;

@Getter
@Setter
public class PetsitterResponse {
    private Long userId;
    private String location;
    private String experience;
    private String profilePhoto;
    private String certification;
    private LocalDateTime createdAt;
}