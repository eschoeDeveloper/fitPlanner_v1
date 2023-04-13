package com.fitplanner.domain.admin.exercise.repository;

import com.fitplanner.domain.admin.exercise.model.AdminFitExerciseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * package     : com.fitplanner.domain.fitExercise.repository
 * file        : FitExerciseCategoryRepository
 * author      : choeuiseung
 * date        : 2022/11/03
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/03                choeuiseung                 최초 생성
 * =======================================================
 */
public interface AdminFitExerciseCategoryRepository extends JpaRepository<AdminFitExerciseCategory, Object>, JpaSpecificationExecutor<AdminFitExerciseCategory> {
}
