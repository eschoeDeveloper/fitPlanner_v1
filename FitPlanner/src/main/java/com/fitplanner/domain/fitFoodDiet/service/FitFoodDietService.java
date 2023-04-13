package com.fitplanner.domain.fitFoodDiet.service;

import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietCategory;
import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietFood;

import java.util.List;
import java.util.Optional;

/**
 * package     : com.fitplanner.domain.fitFoodDiet.service
 * file        : FitFoodDietService
 * author      : choeuiseung
 * date        : 2022/11/05
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/05                choeuiseung                 최초 생성
 * =======================================================
 */
public interface FitFoodDietService {

    List<FitFoodDietCategory> getCategoryList(FitFoodDietCategory fitFoodDietCategory);

    List<FitFoodDietFood> findAll(FitFoodDietFood fitFoodDietFood, int memberSeq);

    Optional<FitFoodDietFood> findOne(FitFoodDietFood fitFoodDietFood);

}
