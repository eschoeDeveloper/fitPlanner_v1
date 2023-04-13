package com.fitplanner.domain.admin.exercise.controller;

import com.fitplanner.core.response.ApiResponse;
import com.fitplanner.domain.admin.exercise.model.AdminFitExercise;
import com.fitplanner.domain.admin.exercise.model.AdminFitExerciseCategory;
import com.fitplanner.domain.admin.exercise.service.AdminFitExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * package     : com.fitplanner.domain.fitExercise.controller
 * file        : FitExerciseController
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
@RequestMapping("/api/admin/fitExercise")
@RequiredArgsConstructor
public class AdminFitExerciseController {

    private final AdminFitExerciseService fitExerciseService;

    @PostMapping(value = "/category/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getCategoryList(
        @RequestBody AdminFitExercise fitExercise,
        HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

            List<AdminFitExerciseCategory> getExerciseCategoryList = fitExerciseService.getExerciseCategoryList();

            apiResponse.setMessage(HttpStatus.OK.name());
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setCount(getExerciseCategoryList.size());
            apiResponse.setData(getExerciseCategoryList);

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

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getExerciseList(
            @RequestBody AdminFitExercise fitExercise,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

            List<AdminFitExercise> getExerciseList = fitExerciseService.getExerciseList(fitExercise);

            apiResponse.setMessage(HttpStatus.OK.name());
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setCount(getExerciseList.size());
            apiResponse.setData(getExerciseList);

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
    public ResponseEntity<ApiResponse> getExerciseInfo(
            @RequestParam("exerciseNo") int exerciseNo,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            AdminFitExercise fitExercise = new AdminFitExercise();
            fitExercise.setExerciseNo(exerciseNo);

            Optional<AdminFitExercise> getExerciseInfo = fitExerciseService.getExerciseInfo(fitExercise);

            apiResponse = new ApiResponse();

            if(getExerciseInfo.isPresent()) {

                apiResponse.setCount(1);
                apiResponse.setData(getExerciseInfo);
                apiResponse.setMessage(HttpStatus.OK.name());
                apiResponse.setCode(HttpStatus.OK.value());

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
