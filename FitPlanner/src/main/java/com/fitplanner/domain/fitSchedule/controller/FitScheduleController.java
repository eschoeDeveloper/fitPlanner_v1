package com.fitplanner.domain.fitSchedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitplanner.core.response.ApiResponse;
import com.fitplanner.core.security.UserDetailsModel;
import com.fitplanner.domain.fitSchedule.model.FitSchedule;
import com.fitplanner.domain.fitSchedule.model.FitSchedulePages;
import com.fitplanner.domain.fitSchedule.service.FitScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * package     : com.fitplanner.domain.fitSchedule.controller
 * file        : FitScheduleController
 * author      : choeuiseung
 * date        : 2022/10/31
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/31                choeuiseung                 최초 생성
 * =======================================================
 */
@Slf4j
@RestController
@RequestMapping("/api/fitSchedule")
@RequiredArgsConstructor
public class FitScheduleController {

    private final FitScheduleService fitScheduleService;

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getScheduleList(
            @AuthenticationPrincipal UserDetailsModel userDetailsModel,
            @RequestBody FitSchedule fitSchedule,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

            fitSchedule.setMemberSeq(userDetailsModel.getUserSeq());

            log.info("fromDate = {}", fitSchedule.getFromDt());
            log.info("toDate = {}", fitSchedule.getToDt());

            List<FitSchedule> getScheduleList = fitScheduleService.getFitScheduleList(fitSchedule);

            apiResponse.setMessage(HttpStatus.OK.name());
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setCount(getScheduleList.size());
            apiResponse.setData(getScheduleList);

        } catch(Exception e) {

            e.printStackTrace();

            apiResponse = new ApiResponse();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/view", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getScheduleView(
            @RequestParam("scheduleNo") int scheduleNo,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            FitSchedule fitSchedule = new FitSchedule();
            fitSchedule.setScheduleNo(scheduleNo);

            apiResponse = new ApiResponse();

            Optional<FitSchedule> fitScheduleInfo = fitScheduleService.getFitScheduleInfo(fitSchedule);

            if(fitScheduleInfo.isPresent()) {

                Optional<FitSchedulePages> fitSchedulePages = fitScheduleService.getFitSchedulePages(fitScheduleInfo.get().getScheduleNo());

                FitSchedule tmp1 = fitScheduleInfo.get();
                FitSchedulePages tmp2 = fitSchedulePages.get();

                ObjectMapper mapper = new ObjectMapper();
                String fitScheduleInfoStr = mapper.writeValueAsString(tmp1);
                String fitSchedulePagesStr = mapper.writeValueAsString(tmp2);

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("fitScheduleInfo", new JSONObject(fitScheduleInfoStr));
                jsonObject.put("fitSchedulePages", new JSONObject(fitSchedulePagesStr));

                apiResponse.setMessage(HttpStatus.OK.name());
                apiResponse.setCode(HttpStatus.OK.value());
                apiResponse.setCount(1);
                apiResponse.setData(jsonObject.toString());

            } else {

                apiResponse.setCount(0);
                apiResponse.setData(Collections.emptyMap());
                apiResponse.setMessage(HttpStatus.NOT_FOUND.name());
                apiResponse.setCode(HttpStatus.NOT_FOUND.value());

            }

        } catch(Exception e) {

            e.printStackTrace();

            apiResponse = new ApiResponse();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createSchedule(
            @AuthenticationPrincipal UserDetailsModel userDetailsModel,
            @RequestBody FitSchedule fitSchedule,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

            fitSchedule.setMemberSeq(userDetailsModel.getUserSeq());

            int complete = fitScheduleService.createFitSchedule(fitSchedule);

            if(complete > 0) {

                apiResponse.setMessage(HttpStatus.OK.name());
                apiResponse.setCode(HttpStatus.OK.value());
                apiResponse.setCount(1);
                apiResponse.setData(complete);

            } else {

                apiResponse.setCount(0);
                apiResponse.setData(Collections.emptyMap());
                apiResponse.setMessage(HttpStatus.NOT_FOUND.name());
                apiResponse.setCode(HttpStatus.NOT_FOUND.value());

            }

        } catch(Exception e) {

            e.printStackTrace();

            apiResponse = new ApiResponse();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

}
