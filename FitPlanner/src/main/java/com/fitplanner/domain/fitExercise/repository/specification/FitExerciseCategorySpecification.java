package com.fitplanner.domain.fitExercise.repository.specification;

import com.fitplanner.domain.fitExercise.model.FitExerciseCategory;
import org.springframework.data.jpa.domain.Specification;

/**
 * package     : com.fitplanner.domain.fitExercise.repository
 * file        : FitExerciseCategorySpecification
 * author      : choeuiseung
 * date        : 2022/11/02
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/02                choeuiseung                 최초 생성
 * =======================================================
 */
public class FitExerciseCategorySpecification {

    public static Specification<FitExerciseCategory> activeUseYn(String useYn) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("useYn"), useYn);
    }

}
