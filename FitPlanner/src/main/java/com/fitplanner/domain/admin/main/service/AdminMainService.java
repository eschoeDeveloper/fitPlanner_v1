package com.fitplanner.domain.admin.main.service;

import java.util.List;
import java.util.Map;

public interface AdminMainService {

    List<Map<String, Object>> getExerciseRankList();
    List<Map<String, Object>> getMemberAgeList();
    List<Map<String, Object>> getMemberSexList();

}
