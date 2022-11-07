package com.fitplanner.domain.fitExercise.repository;

import com.fitplanner.domain.fitExercise.model.FitExercise;
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
public interface FitExerciseRepository extends JpaRepository<FitExercise, Object>, JpaSpecificationExecutor<FitExercise> {
}
