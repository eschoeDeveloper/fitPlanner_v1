package com.fitplanner.domain.fitFoodDiet.repository.specification;

import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietMember;
import org.springframework.data.jpa.domain.Specification;

public class FitFoodDietMemberSpecification {

    public static Specification<FitFoodDietMember> selectMemberSeq(int memberSeq) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("memberSeq"), memberSeq);
    }

}
