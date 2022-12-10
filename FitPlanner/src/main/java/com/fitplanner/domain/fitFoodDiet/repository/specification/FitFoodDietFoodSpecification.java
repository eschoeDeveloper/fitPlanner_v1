package com.fitplanner.domain.fitFoodDiet.repository.specification;

import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietFood;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * package     : com.fitplanner.domain.fitFoodDiet.repository.specification
 * file        : FitFoodDietFood
 * author      : choeuiseung
 * date        : 2022/11/05
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/05                choeuiseung                 최초 생성
 * =======================================================
 */
public class FitFoodDietFoodSpecification {

    public static Specification<FitFoodDietFood> selectFoodMulti(String foodIdx) {

        List<Predicate> predicates = new ArrayList<>();

        List<String> foodNoList = Arrays.asList(foodIdx.split(","));

        return (root, query, criteriaBuilder) -> {
            predicates.add(root.get("foodNo").in(foodNoList));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }

    public static Specification<FitFoodDietFood> selectFood(int foodNo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("foodNo"), foodNo);
    }

}
