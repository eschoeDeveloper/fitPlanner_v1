package com.fitplanner.domain.fitFoodDiet.repository;

import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * package     : com.fitplanner.domain.fitFoodDiet.repository
 * file        : FitFoodDietCategoryRepository
 * author      : choeuiseung
 * date        : 2022/11/05
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/05                choeuiseung                 최초 생성
 * =======================================================
 */
public interface FitFoodDietCategoryRepository extends JpaRepository<FitFoodDietCategory, Object>, JpaSpecificationExecutor<FitFoodDietCategory> {
}
