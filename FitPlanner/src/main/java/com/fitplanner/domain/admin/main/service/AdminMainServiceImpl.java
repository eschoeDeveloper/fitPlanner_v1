package com.fitplanner.domain.admin.main.service;

import com.fitplanner.domain.admin.main.repository.AdminMainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("adminMainService")
@RequiredArgsConstructor
public class AdminMainServiceImpl implements AdminMainService {

    private final AdminMainRepository adminMainRepository;

    @Override
    public List<Map<String, Object>> getExerciseRankList() {
       return adminMainRepository.getExerciseRankList();
    }

    @Override
    public List<Map<String, Object>> getMemberAgeList() {
        return adminMainRepository.getMemberAgeList();
    }

    @Override
    public List<Map<String, Object>> getMemberSexList() {
        return adminMainRepository.getMemberSexList();
    }

}
