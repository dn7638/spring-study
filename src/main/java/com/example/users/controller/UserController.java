package com.example.users.controller;

import com.example.users.domain.User;
import com.example.users.dto.UserRegisterRequest;
import com.example.users.dto.UserUpdateRequest;
import com.example.users.dto.UserLoginRequest;
import com.example.users.dto.UserResponse;
import com.example.users.repository.UserRepository;
import com.example.users.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // ✅ 사용자 상세정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        UserResponse user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // ✅ 사용자 정보 수정 (본인만 가능)
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request
    ) {
        UserResponse updatedUser = userService.updateUser(userId, request);
        return ResponseEntity.ok(updatedUser);
    }

    // ✅ 사용자 삭제 (관리자만 가능)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(
            @PathVariable Long userId,
            HttpSession session) {
        
        Map<String, String> response = new HashMap<>();
        
        // Check authentication
        Long loggedInUserId = (Long) session.getAttribute("user");
        if (loggedInUserId == null) {
            response.put("message", "로그인이 필요합니다");
            return ResponseEntity.status(401).body(response);
        }
        
        // Authorization check
        try {
            User user = userRepository.findById(loggedInUserId)
                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));
            
            if (!loggedInUserId.equals(userId) && !"ADMIN".equals(user.getRole())) {
                response.put("message", "권한이 없습니다");
                return ResponseEntity.status(403).body(response);
            }
            
            userService.deleteUser(userId, loggedInUserId);
            session.invalidate();
            response.put("message", "회원 탈퇴가 완료되었습니다");
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest request, HttpSession session) {
        UserResponse response = userService.login(request, session);
        session.setMaxInactiveInterval(1800); // 30분 (초 단위)
        session.setAttribute("user", response.getUserId());
        
        log.info("{}", session.getAttribute("user"));
        return ResponseEntity.ok()
                .header("Set-Cookie", "JSESSIONID=" + session.getId() + "; Path=/; HttpOnly; SameSite=Lax")
                .body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 완료");
    }
}
