package com.example.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private String nationality;
    private String job;
}
