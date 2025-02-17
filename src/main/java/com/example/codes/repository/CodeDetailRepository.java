package com.example.codes.repository;

import com.example.codes.domain.CodeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, String> {
    
    // 그룹 ID로 코드 디테일 조회 메서드 추가
    List<CodeDetail> findByCodeGroup_GroupId(String groupId);
} 