package com.example.codes.service;

import com.example.codes.domain.CodeDetail;
import com.example.codes.domain.CodeGroup;
import com.example.codes.dto.CodeDetailRequest;
import com.example.codes.dto.CodeDetailResponse;
import com.example.codes.repository.CodeDetailRepository;
import com.example.codes.repository.CodeGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeDetailService {

    private final CodeDetailRepository codeDetailRepository;
    private final CodeGroupRepository codeGroupRepository;

    public CodeDetailService(CodeDetailRepository codeDetailRepository, 
                           CodeGroupRepository codeGroupRepository) {
        this.codeDetailRepository = codeDetailRepository;
        this.codeGroupRepository = codeGroupRepository;
    }

    public CodeDetailResponse createDetail(CodeDetailRequest request) {
        CodeGroup group = codeGroupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));
        
        CodeDetail detail = new CodeDetail();
        detail.setCodeId(request.getCodeId());
        detail.setCodeGroup(group);
        detail.setCodeName(request.getCodeName());
        detail.setCodeValue(request.getCodeValue());
        detail.setSortOrder(request.getSortOrder());
        detail.setActive(request.isActive());
        
        return toResponse(codeDetailRepository.save(detail));
    }

    public List<CodeDetailResponse> getDetailsByGroup(String groupId) {
        return codeDetailRepository.findByCodeGroup_GroupId(groupId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CodeDetailResponse updateDetail(String codeId, CodeDetailRequest request) {
        CodeDetail detail = codeDetailRepository.findById(codeId)
                .orElseThrow(() -> new RuntimeException("Detail not found"));
        
        if(request.getGroupId() != null) {
            CodeGroup group = codeGroupRepository.findById(request.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group not found"));
            detail.setCodeGroup(group);
        }
        
        detail.setCodeName(request.getCodeName());
        detail.setCodeValue(request.getCodeValue());
        detail.setSortOrder(request.getSortOrder());
        detail.setActive(request.isActive());
        
        return toResponse(codeDetailRepository.save(detail));
    }

    public void deleteDetail(String codeId) {
        codeDetailRepository.deleteById(codeId);
    }

    private CodeDetailResponse toResponse(CodeDetail detail) {
        CodeDetailResponse response = new CodeDetailResponse();
        response.setCodeId(detail.getCodeId());
        response.setGroupId(detail.getCodeGroup().getGroupId());
        response.setCodeName(detail.getCodeName());
        response.setCodeValue(detail.getCodeValue());
        response.setSortOrder(detail.getSortOrder());
        response.setActive(detail.isActive());
        response.setCreatedAt(detail.getCreatedAt());
        response.setUpdatedAt(detail.getUpdatedAt());
        return response;
    }
} 