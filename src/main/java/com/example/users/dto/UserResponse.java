package com.example.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long userId;
    private String email;
    private String name;
    private String nickname;
    private String phone;
    private String role;

    public UserResponse(Long userId, String email, String name, String nickname, String phone, String role) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.role = role;
    }
}
