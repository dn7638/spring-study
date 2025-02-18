package com.example.bookings.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
public class BookingResponse {
    private Long id;
    private LocalDateTime bookingDate;
    private String status;
    private LocalDateTime createdAt;
    private UserInfo user;
    private PetsitterInfo petsitter;

    @Getter @Setter
    public static class UserInfo {
        private Long userId;
        private String name;
        private String nickname;
    }

    @Getter @Setter
    public static class PetsitterInfo {
        private Long petsitterId;
        private String location;
        private String experience;
    }
} 