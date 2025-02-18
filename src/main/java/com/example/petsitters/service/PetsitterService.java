package com.example.petsitters.service;

import com.example.petsitters.domain.Petsitter;
import com.example.petsitters.dto.PetsitterRequest;
import com.example.petsitters.dto.PetsitterResponse;
import com.example.petsitters.repository.PetsitterRepository;
import com.example.users.domain.User;
import com.example.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetsitterService {

    @Autowired
    private PetsitterRepository petsitterRepository;

    @Autowired
    private UserRepository userRepository;

    public Petsitter createPetsitter(Long userId, PetsitterRequest petsitterRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Petsitter petsitter = new Petsitter();
        petsitter.setUser(user);
        petsitter.setLocation(petsitterRequest.getLocation());
        petsitter.setExperience(petsitterRequest.getExperience());
        petsitter.setProfilePhoto(petsitterRequest.getProfilePhoto());
        petsitter.setCertification(petsitterRequest.getCertification());
        return petsitterRepository.save(petsitter);
    }

    public Optional<Petsitter> getPetsitterById(Long id) {
        return petsitterRepository.findById(id);
    }

    public List<PetsitterResponse> getAllPetsitters() {
        return petsitterRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PetsitterResponse convertToDto(Petsitter petsitter) {
        PetsitterResponse response = new PetsitterResponse();
        response.setId(petsitter.getId());
        response.setLocation(petsitter.getLocation());
        response.setExperience(petsitter.getExperience());
        response.setProfilePhoto(petsitter.getProfilePhoto());
        response.setCertification(petsitter.getCertification());
        response.setCreatedAt(petsitter.getCreatedAt());
        
        User user = petsitter.getUser();
        PetsitterResponse.UserInfo userInfo = new PetsitterResponse.UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setName(user.getName());
        userInfo.setNickname(user.getNickname());
        response.setUser(userInfo);
        
        return response;
    }

    public void deletePetsitter(Long id) {
        petsitterRepository.deleteById(id);
    }

    public Petsitter updatePetsitter(Long userId, Long petsitterId, PetsitterRequest petsitterRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return petsitterRepository.findById(petsitterId)
                .map(petsitter -> {
                    petsitter.setUser(user);
                    petsitter.setLocation(petsitterRequest.getLocation());
                    petsitter.setExperience(petsitterRequest.getExperience());
                    petsitter.setProfilePhoto(petsitterRequest.getProfilePhoto());
                    petsitter.setCertification(petsitterRequest.getCertification());
                    return petsitterRepository.save(petsitter);
                })
                .orElseThrow(() -> new RuntimeException("Petsitter not found"));
    }
} 