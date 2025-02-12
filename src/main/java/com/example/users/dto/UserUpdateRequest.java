package com.example.users.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private String name;
    private String nickname;
    private String phone;
}
