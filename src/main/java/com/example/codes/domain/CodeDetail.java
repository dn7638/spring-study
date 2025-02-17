package com.example.codes.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "code_detail")
public class CodeDetail {

    @Id
    @Column(name = "code_id", length = 50)
    private String codeId;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private CodeGroup codeGroup;

    @Column(name = "code_name", nullable = false, length = 100)
    private String codeName;

    @Column(name = "code_value", nullable = false, length = 50)
    private String codeValue;

    @Column(name = "sort_order")
    private int sortOrder = 1;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Getters and Setters
    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public CodeGroup getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(CodeGroup codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 