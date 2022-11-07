package com.fitplanner.domain.fitFoodDiet.controller;

import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietCategory;
import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietFood;
import com.fitplanner.domain.fitFoodDiet.service.FitFoodDietService;
import com.fitplanner.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * package     : com.fitplanner.domain.fitFoodDiet.controller
 * file        : FitFoodDietController
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
@RequestMapping("/api/fitFoodDiet")
@RequiredArgsConstructor
public class FitFoodDietController {

    private final FitFoodDietService fitFoodDietService;

    @GetMapping(value = "/category/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getFoodDietFoodList(
            @RequestBody FitFoodDietCategory fitFoodDietCategory,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            List<FitFoodDietCategory> getFoodCategoryList = fitFoodDietService.getCategoryList(fitFoodDietCategory);

            apiResponse = new ApiResponse();

            apiResponse.setMessage(HttpStatus.OK.name());
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setCount(getFoodCategoryList.size());
            apiResponse.setData(getFoodCategoryList);

        } catch(Exception e) {

            e.printStackTrace();

            apiResponse = new ApiResponse();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyList());

        }

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getFoodDietFoodList(
            @RequestBody FitFoodDietFood fitFoodDietFood,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            List<FitFoodDietFood> getFoodList = fitFoodDietService.findAll(fitFoodDietFood);

            apiResponse = new ApiResponse();

            apiResponse.setMessage(HttpStatus.OK.name());
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setCount(getFoodList.size());
            apiResponse.setData(getFoodList);

        } catch(Exception e) {

            e.printStackTrace();

            apiResponse = new ApiResponse();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyList());

        }

        return ResponseEntity.ok(apiResponse);

    }


}
