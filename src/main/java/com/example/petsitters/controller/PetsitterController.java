package com.example.petsitters.controller;

import com.example.petsitters.domain.Petsitter;
import com.example.petsitters.dto.PetsitterRequest;
import com.example.petsitters.service.PetsitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/petsitters")
public class PetsitterController {

    @Autowired
    private PetsitterService petsitterService;

    @PostMapping("/{userId}")
    public ResponseEntity<Petsitter> createPetsitter(@PathVariable Long userId, @RequestBody PetsitterRequest petsitterRequest) {
        Petsitter createdPetsitter = petsitterService.createPetsitter(userId, petsitterRequest);
        return ResponseEntity.ok(createdPetsitter);
    }

    @GetMapping("/{petsitterId}")
    public ResponseEntity<Petsitter> getPetsitterById(@PathVariable Long petsitterId) {
        Optional<Petsitter> petsitter = petsitterService.getPetsitterById(petsitterId);
        return petsitter.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{petsitterId}")
    public ResponseEntity<Void> deletePetsitter(@PathVariable Long petsitterId) {
        petsitterService.deletePetsitter(petsitterId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{petsitterId}")
    public ResponseEntity<Petsitter> updatePetsitter(@PathVariable Long petsitterId, @RequestBody PetsitterRequest petsitterRequest, HttpSession session) {
        // 전달받은 persisterId와 was에 저장된 세션의 userId가 일치하는지 확인
        Long userId = (Long) session.getAttribute("user");  
        // Long userId = 1L; // 테스트용

        // 일치하는 경우와 일치하지 않는 경우 처리
        if (userId == null) {
            return ResponseEntity.status(402).build();
        }
        if (userId != petsitterId) {
            return ResponseEntity.status(403).build();
        }   
        Petsitter updatedPetsitter = petsitterService.updatePetsitter(userId, petsitterId, petsitterRequest);
        return ResponseEntity.ok(updatedPetsitter);
    }
} 