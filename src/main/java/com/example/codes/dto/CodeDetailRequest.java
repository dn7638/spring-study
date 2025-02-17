package com.example.codes.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter @Setter
public class CodeDetailRequest {
    private String codeId;
    private String groupId;
    private String codeName;
    @NotBlank(message = "codeValue는 필수 입력 값입니다.")
    private String codeValue;
    @NotNull(message = "sortOrder는 필수 입력 값입니다.")
    private int sortOrder;
    @NotNull(message = "isActive는 필수 입력 값입니다.")
    private boolean isActive;
} 