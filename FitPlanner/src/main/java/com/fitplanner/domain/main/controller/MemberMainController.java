package com.fitplanner.domain.main.controller;

import com.fitplanner.core.response.ApiResponse;
import com.fitplanner.core.security.UserDetailsModel;
import com.fitplanner.domain.main.service.MemberMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/member/main")
@RequiredArgsConstructor
public class MemberMainController {

    private final MemberMainService memberMainService;

    @PostMapping(value="/data")
    public ResponseEntity<ApiResponse> getMemberMainData(
      @AuthenticationPrincipal UserDetailsModel userDetailsModel
    ) {

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

            JSONObject jsonObject = new JSONObject();

            List<Map<String, Object>> getFitScheduleList = memberMainService.getFitScheduleList(userDetailsModel.getUserSeq());
            List<Map<String, Object>> getFoodDietList = memberMainService.getFoodDietList(userDetailsModel.getUserSeq());
            JSONObject getFitHealthGymJson = memberMainService.getFitHealthGymList(userDetailsModel.getUserSeq());

            int dataCount = getFitScheduleList.size() + getFitHealthGymJson.getInt("total") + getFoodDietList.size();

            JSONArray getFitScheduleJsonArray = new JSONArray(getFitScheduleList);
            JSONArray getFoodDietJsonArray = new JSONArray(getFoodDietList);

            jsonObject.put("fitScheduleList", getFitScheduleJsonArray);
            jsonObject.put("fitFoodDietList", getFoodDietJsonArray);
            jsonObject.put("fitHealthGymList", getFitHealthGymJson.get("items").toString());

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
