package com.example.bookings.controller;

import com.example.bookings.dto.BookingRequest;
import com.example.bookings.dto.BookingResponse;
import com.example.bookings.service.BookingService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @RequestBody BookingRequest request,
            HttpSession session) {
        
        Long userId = (Long) session.getAttribute("user");
        logger.info("세션 확인 - ID: {} | User ID: {}", session.getId(), userId);
        
        if(userId == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }
        
        return ResponseEntity.ok(bookingService.createBooking(request, userId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<BookingResponse>> getUserBookings(HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }

    @PatchMapping("/{bookingId}/status")
    public ResponseEntity<BookingResponse> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam String status) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(bookingId, status));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
} 