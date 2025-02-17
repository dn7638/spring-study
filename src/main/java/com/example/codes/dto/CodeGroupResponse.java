package com.example.codes.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CodeGroupResponse {
    private String groupId;
    private String groupName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 