package com.example.petsitters.repository;

import com.example.petsitters.domain.Petsitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetsitterRepository extends JpaRepository<Petsitter, Long> {
} 