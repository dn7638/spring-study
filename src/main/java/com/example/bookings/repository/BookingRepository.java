package com.example.bookings.repository;

import com.example.bookings.domain.Booking;
import com.example.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser_UserId(Long userId);
    List<Booking> findByPetsitterId(Long petsitterId);
    boolean existsByUserAndBookingDate(User user, LocalDateTime bookingDate);
    boolean existsByPetsitterIdAndBookingDate(Long petsitterId, LocalDateTime bookingDate);
} 