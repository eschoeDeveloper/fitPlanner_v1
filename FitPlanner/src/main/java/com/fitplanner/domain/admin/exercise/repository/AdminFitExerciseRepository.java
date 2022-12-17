package com.fitplanner.domain.admin.exercise.repository;

import com.fitplanner.domain.admin.exercise.model.AdminFitExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * package     : com.fitplanner.domain.fitExercise.repository
 * file        : FitExerciseRepository
 * author      : choeuiseung
 * date        : 2022/11/02
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/02                choeuiseung                 최초 생성
 * =======================================================
 */
public interface AdminFitExerciseRepository extends JpaRepository<AdminFitExercise, Object>, JpaSpecificationExecutor<AdminFitExercise> {
}
