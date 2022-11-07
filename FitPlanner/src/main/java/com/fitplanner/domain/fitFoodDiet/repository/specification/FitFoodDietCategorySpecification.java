package com.fitplanner.domain.fitFoodDiet.repository.specification;

import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietCategory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * package     : com.fitplanner.domain.fitFoodDiet.repository.specification
 * file        : FitFoodDietCategorySpecification
 * author      : choeuiseung
 * date        : 2022/11/05
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/05                choeuiseung                 최초 생성
 * =======================================================
 */
public class FitFoodDietCategorySpecification {

    public static Specification<FitFoodDietCategory> checkUseYn( String useYn ) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("useYn"), useYn);
    }

}
