package com.example.codes.controller;

import com.example.codes.dto.CodeDetailRequest;
import com.example.codes.dto.CodeDetailResponse;
import com.example.codes.service.CodeDetailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/code-details")
@Validated
public class CodeDetailController {

    private final CodeDetailService codeDetailService;

    public CodeDetailController(CodeDetailService codeDetailService) {
        this.codeDetailService = codeDetailService;
    }

    @PostMapping
    public CodeDetailResponse createDetail(@Valid @RequestBody CodeDetailRequest request) {
        return codeDetailService.createDetail(request);
    }

    @GetMapping("/group/{groupId}")
    public List<CodeDetailResponse> getDetailsByGroup(@PathVariable String groupId) {
        return codeDetailService.getDetailsByGroup(groupId);
    }

    @PutMapping("/{codeId}")
    public CodeDetailResponse updateDetail(@PathVariable String codeId, 
                                         @Valid @RequestBody CodeDetailRequest request) {
        return codeDetailService.updateDetail(codeId, request);
    }

    @DeleteMapping("/{codeId}")
    public void deleteDetail(@PathVariable String codeId) {
        codeDetailService.deleteDetail(codeId);
    }
} 