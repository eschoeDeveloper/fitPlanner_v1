package com.fitplanner.domain.admin.exercise.repository.specification;

import com.fitplanner.domain.admin.exercise.model.AdminFitExercise;
import org.springframework.data.jpa.domain.Specification;

/**
 * package     : com.fitplanner.domain.fitExercise.repository
 * file        : FitExerciseSpecification
 * author      : choeuiseung
 * date        : 2022/11/03
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/03                choeuiseung                 최초 생성
 * =======================================================
 */
public class FitExerciseSpecification {

    public static Specification<AdminFitExercise> selectExercise(int exerciseNo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("exerciseNo"), exerciseNo);
    }

}
