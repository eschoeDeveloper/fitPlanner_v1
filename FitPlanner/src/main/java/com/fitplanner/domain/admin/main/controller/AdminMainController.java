package com.fitplanner.domain.admin.main.controller;

import com.fitplanner.core.response.ApiResponse;
import com.fitplanner.core.security.UserDetailsModel;
import com.fitplanner.domain.admin.main.service.AdminMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin/main")
@RequiredArgsConstructor
public class AdminMainController {

    private final AdminMainService adminMainService;

    @PostMapping(value="/data")
    public ResponseEntity<ApiResponse> getMemberMainData(
            @AuthenticationPrincipal UserDetailsModel userDetailsModel
    ) {

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

            JSONObject jsonObject = new JSONObject();

            List<Map<String, Object>> getExerciseRankList = adminMainService.getExerciseRankList();
            List<Map<String, Object>> getMemberAgeList = adminMainService.getMemberAgeList();
            List<Map<String, Object>> getMemberSexList = adminMainService.getMemberSexList();

            int dataCount = getExerciseRankList.size() + getMemberAgeList.size() + getMemberSexList.size();

            JSONArray getExerciseRank = new JSONArray(getExerciseRankList);
            JSONArray getMemberAge = new JSONArray(getMemberAgeList);
            JSONArray getMemberSex = new JSONArray(getMemberSexList);

            jsonObject.put("getExerciseRankList", getExerciseRank);
            jsonObject.put("getMemberAgeList", getMemberAge);
            jsonObject.put("getMemberSexList", getMemberSex);

            apiResponse.setData(jsonObject.toString());
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setCount(dataCount);
            apiResponse.setMessage(HttpStatus.OK.name());

        } catch( Exception e ) {

            e.printStackTrace();

            apiResponse = new ApiResponse();

            apiResponse.setData(Collections.emptyMap());
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setCount(0);
            apiResponse.setMessage(HttpStatus.NOT_FOUND.name());

        }

        return ResponseEntity.ok(apiResponse);

    }

}
