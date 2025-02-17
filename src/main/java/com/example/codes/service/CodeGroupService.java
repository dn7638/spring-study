package com.example.codes.service;

import com.example.codes.domain.CodeGroup;
import com.example.codes.dto.CodeGroupRequest;
import com.example.codes.dto.CodeGroupResponse;
import com.example.codes.repository.CodeGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeGroupService {

    private final CodeGroupRepository codeGroupRepository;

    public CodeGroupService(CodeGroupRepository codeGroupRepository) {
        this.codeGroupRepository = codeGroupRepository;
    }

    public CodeGroupResponse createGroup(CodeGroupRequest request) {
        CodeGroup group = new CodeGroup();
        group.setGroupId(request.getGroupId());
        group.setGroupName(request.getGroupName());
        group.setDescription(request.getDescription());
        return toResponse(codeGroupRepository.save(group));
    }

    public List<CodeGroupResponse> getAllGroups() {
        return codeGroupRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CodeGroupResponse updateGroup(String groupId, CodeGroupRequest request) {
        CodeGroup group = codeGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        group.setGroupName(request.getGroupName());
        group.setDescription(request.getDescription());
        return toResponse(codeGroupRepository.save(group));
    }

    public void deleteGroup(String groupId) {
        codeGroupRepository.deleteById(groupId);
    }

    private CodeGroupResponse toResponse(CodeGroup group) {
        CodeGroupResponse response = new CodeGroupResponse();
        response.setGroupId(group.getGroupId());
        response.setGroupName(group.getGroupName());
        response.setDescription(group.getDescription());
        response.setCreatedAt(group.getCreatedAt());
        response.setUpdatedAt(group.getUpdatedAt());
        return response;
    }
} 