package com.example.petsitters.dto;

import java.time.LocalDateTime;

import lombok.Getter;   
import lombok.Setter;

@Getter @Setter
public class PetsitterResponse {
    private Long id;
    private String location;
    private String experience;
    private String profilePhoto;
    private String certification;
    private LocalDateTime createdAt;
    private UserInfo user;

    @Getter @Setter
    public static class UserInfo {
        private Long userId;
        private String name;
        private String nickname;
    }
}