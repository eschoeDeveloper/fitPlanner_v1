package com.fitplanner.domain.admin.exercise.service;

import com.fitplanner.domain.admin.exercise.model.AdminFitExercise;
import com.fitplanner.domain.admin.exercise.model.AdminFitExerciseCategory;

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
public interface AdminFitExerciseService {

    // 운동종목 카테고리 목록
    List<AdminFitExerciseCategory> getExerciseCategoryList();

    List<AdminFitExercise> getExerciseList(AdminFitExercise fitExercise);

    Optional<AdminFitExercise> getExerciseInfo(AdminFitExercise fitExercise);

}
