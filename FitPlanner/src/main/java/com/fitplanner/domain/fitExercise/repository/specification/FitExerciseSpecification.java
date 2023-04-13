package com.fitplanner.domain.fitExercise.repository.specification;

import com.fitplanner.domain.fitExercise.model.FitExercise;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

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

    public static Specification<FitExercise> checkUseYn(String useYn) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("useYn"), useYn);
    }

    public static Specification<FitExercise> selectCategory(String categoryIdx) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("categoryIdx"), "%" + categoryIdx + "%");
    }

    public static Specification<FitExercise> selectExercise(int exerciseNo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("exerciseNo"), exerciseNo);
    }

}
