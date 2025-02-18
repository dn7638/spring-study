package com.example.bookings.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
public class BookingRequest {
    @NotNull(message = "펫시터 ID는 필수 입력 값입니다")
    private Long petsitterId;
    
    @Future(message = "예약 날짜는 미래 시간이어야 합니다")
    private LocalDateTime bookingDate;
    
    private String status;
} 