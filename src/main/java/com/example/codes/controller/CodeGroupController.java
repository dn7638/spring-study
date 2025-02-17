package com.example.codes.controller;

import com.example.codes.dto.CodeGroupRequest;
import com.example.codes.dto.CodeGroupResponse;
import com.example.codes.service.CodeGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/code-groups")
public class CodeGroupController {

    private final CodeGroupService codeGroupService;

    public CodeGroupController(CodeGroupService codeGroupService) {
        this.codeGroupService = codeGroupService;
    }

    @PostMapping
    public CodeGroupResponse createGroup(@RequestBody CodeGroupRequest request) {
        return codeGroupService.createGroup(request);
    }

    @GetMapping
    public List<CodeGroupResponse> getAllGroups() {
        return codeGroupService.getAllGroups();
    }

    @PutMapping("/{groupId}")
    public CodeGroupResponse updateGroup(@PathVariable String groupId, 
                                       @RequestBody CodeGroupRequest request) {
        return codeGroupService.updateGroup(groupId, request);
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable String groupId) {
        codeGroupService.deleteGroup(groupId);
    }
} 