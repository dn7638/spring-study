package com.example.petsitters.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetsitterRequest {
    private String location;
    private String experience;
    private String profilePhoto;
    private String certification;
}
