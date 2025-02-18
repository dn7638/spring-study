package com.example.users.service;

import com.example.users.domain.User;
import com.example.users.dto.UserRegisterRequest;
import com.example.users.dto.UserUpdateRequest;
import com.example.users.dto.UserLoginRequest;
import com.example.users.dto.UserResponse;
import com.example.users.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse register(UserRegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));  // 비밀번호 암호화
        user.setName(request.getName());
        user.setNickname(request.getNickname());
        user.setPhone(request.getPhone());
        user.setNationality(request.getNationality());
        user.setJob(request.getJob());
        user.setRole("USER");

        userRepository.save(user);

        return new UserResponse(user.getUserId(), user.getEmail(), user.getName(), user.getNickname(), user.getPhone(), user.getRole());
    }

    public UserResponse login(UserLoginRequest request, HttpSession session) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty() || !passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())) {
            throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        User user = optionalUser.get();
        session.setAttribute("user", user.getUserId());  // 세션 저장

        return new UserResponse(user.getUserId(), user.getEmail(), user.getName(), user.getNickname(), user.getPhone(), user.getRole());
    }

// ✅ 사용자 목록 조회 (관리자만 가능)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
            .map(user -> new UserResponse(
                user.getUserId(),
                user.getEmail(),
                user.getName(),
                user.getNickname(),
                user.getPhone(),
                user.getRole()
            ))
            .collect(Collectors.toList());
    }

    // ✅ 사용자 상세정보 조회
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return new UserResponse(user.getUserId(), user.getEmail(), user.getName(),
                user.getNickname(), user.getPhone(), user.getRole());
    }

    // ✅ 사용자 정보 수정 (본인만 가능)
    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.setName(request.getName());
        user.setNickname(request.getNickname());
        user.setPhone(request.getPhone());

        return new UserResponse(user.getUserId(), user.getEmail(), user.getName(),
                user.getNickname(), user.getPhone(), user.getRole());
    }

    // ✅ 사용자 삭제 (관리자만 가능)
    public void deleteUser(Long userId, Long requesterId) {
        User requester = userRepository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // 관리자가 아니면서 본인이 아닌 경우
        if (!requesterId.equals(userId) && !"ADMIN".equals(requester.getRole())) {
            throw new RuntimeException("권한이 없습니다");
        }
        
        userRepository.deleteById(userId);
    }


}
