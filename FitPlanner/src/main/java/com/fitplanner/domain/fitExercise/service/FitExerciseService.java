package com.fitplanner.domain.fitExercise.service;

import com.fitplanner.domain.fitExercise.model.FitExercise;
import com.fitplanner.domain.fitExercise.model.FitExerciseCategory;

import java.util.List;
import java.util.Optional;

/**
 * package     : com.fitplanner.domain.fitExercise.service
 * file        : FitExerciseService
 * author      : choeuiseung
 * date        : 2022/11/02
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/02                choeuiseung                 최초 생성
 * =======================================================
 */
public interface FitExerciseService {

    // 운동종목 카테고리 목록
    List<FitExerciseCategory> getExerciseCategoryList();

    List<FitExercise> getExerciseList(FitExercise fitExercise);

    Optional<FitExercise> getExerciseInfo(FitExercise fitExercise);

}
