package com.example.bookings.controller;

import com.example.bookings.dto.BookingRequest;
import com.example.bookings.dto.BookingResponse;
import com.example.bookings.service.BookingService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @RequestBody BookingRequest request,
            HttpSession session) {
        
        Long userId = (Long) session.getAttribute("user");
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