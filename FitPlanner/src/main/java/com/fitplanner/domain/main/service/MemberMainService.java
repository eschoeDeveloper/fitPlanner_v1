package com.fitplanner.domain.main.service;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface MemberMainService {

    List<Map<String, Object>> getFitScheduleList(int memberSeq);
    List<Map<String, Object>> getFoodDietList(int memberSeq);
    JSONObject getFitHealthGymList(int memberSeq);

}
