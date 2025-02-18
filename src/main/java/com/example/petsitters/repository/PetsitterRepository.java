package com.example.petsitters.repository;

import com.example.petsitters.domain.Petsitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetsitterRepository extends JpaRepository<Petsitter, Long> {
    Optional<Petsitter> findById(Long id);
    Optional<Petsitter> findByUser_UserId(Long userId);
} 