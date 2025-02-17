package com.example.codes.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CodeDetailResponse {
    private String codeId;
    private String groupId;
    private String codeName;
    private String codeValue;
    private int sortOrder;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 