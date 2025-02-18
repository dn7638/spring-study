package com.example.bookings.service;

import com.example.bookings.domain.Booking;
import com.example.bookings.dto.BookingRequest;
import com.example.bookings.dto.BookingResponse;
import com.example.bookings.repository.BookingRepository;
import com.example.petsitters.domain.Petsitter;
import com.example.petsitters.repository.PetsitterRepository;
import com.example.users.domain.User;
import com.example.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.bookings.exception.ConflictException;
import com.example.bookings.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PetsitterRepository petsitterRepository;

    // 상태 유효성 검사 메서드
    private void validateStatus(String status) {
        List<String> allowedStatus = List.of("REQUESTED", "CONFIRMED", "CANCELLED", "COMPLETED");
        if (!allowedStatus.contains(status)) {
            throw new IllegalArgumentException("유효하지 않은 상태 값입니다");
        }
    }

    // 펫시터 예약 가능 시간 확인
    public boolean isPetsitterAvailable(Long petsitterId, LocalDateTime date) {
        return !bookingRepository.existsByPetsitterIdAndBookingDate(petsitterId, date);
    }

    @Transactional
    public BookingResponse createBooking(BookingRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다"));
        
        Petsitter petsitter = petsitterRepository.findById(request.getPetsitterId())
                .orElseThrow(() -> new ResourceNotFoundException("펫시터를 찾을 수 없습니다"));

        // 중복 예약 체크
        if (bookingRepository.existsByUserAndBookingDate(user, request.getBookingDate())) {
            throw new ConflictException("이미 해당 시간에 예약이 존재합니다");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setPetsitter(petsitter);
        booking.setBookingDate(request.getBookingDate());
        booking.setStatus(request.getStatus() != null ? request.getStatus() : "REQUESTED");

        Booking savedBooking = bookingRepository.save(booking);
        return convertToDto(savedBooking);
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getUserBookings(Long userId) {
        return bookingRepository.findByUser_UserId(userId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingResponse updateBookingStatus(Long bookingId, String status) {
        validateStatus(status);
        
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("예약을 찾을 수 없습니다"));
        booking.setStatus(status);
        
        return convertToDto(bookingRepository.save(booking));
    }

    @Transactional
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    private BookingResponse convertToDto(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setBookingDate(booking.getBookingDate());
        response.setStatus(booking.getStatus());
        response.setCreatedAt(booking.getCreatedAt());

        // User 정보 매핑
        BookingResponse.UserInfo userInfo = new BookingResponse.UserInfo();
        userInfo.setUserId(booking.getUser().getUserId());
        userInfo.setName(booking.getUser().getName());
        userInfo.setNickname(booking.getUser().getNickname());
        response.setUser(userInfo);

        // Petsitter 정보 매핑
        BookingResponse.PetsitterInfo petsitterInfo = new BookingResponse.PetsitterInfo();
        petsitterInfo.setPetsitterId(booking.getPetsitter().getId());
        petsitterInfo.setLocation(booking.getPetsitter().getLocation());
        petsitterInfo.setExperience(booking.getPetsitter().getExperience());
        response.setPetsitter(petsitterInfo);

        return response;
    }
}