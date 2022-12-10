package com.fitplanner.domain.fitFoodDiet.repository;

import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FitFoodDietMemberRepository extends JpaRepository<FitFoodDietMember, Object>, JpaSpecificationExecutor<FitFoodDietMember> {

}
