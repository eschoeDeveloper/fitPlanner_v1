package com.fitplanner.domain.fitFoodDiet.service;

import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietCategory;
import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietFood;
import com.fitplanner.domain.fitFoodDiet.repository.FitFoodDietCategoryRepository;
import com.fitplanner.domain.fitFoodDiet.repository.FitFoodDietFoodRepository;
import com.fitplanner.domain.fitFoodDiet.repository.specification.FitFoodDietCategorySpecification;
import com.fitplanner.domain.fitFoodDiet.repository.specification.FitFoodDietFoodSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * package     : com.fitplanner.domain.fitFoodDiet.service
 * file        : FitFoodDietServiceImpl
 * author      : choeuiseung
 * date        : 2022/11/05
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/05                choeuiseung                 최초 생성
 * =======================================================
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FitFoodDietServiceImpl implements FitFoodDietService {

    private final FitFoodDietCategoryRepository fitFoodDietCategoryRepository;

    private final FitFoodDietFoodRepository fitFoodDietFoodRepository;

    @Override
    public List<FitFoodDietCategory> getCategoryList(FitFoodDietCategory fitFoodDietCategory) {

        Specification<FitFoodDietCategory> specification = Specification
                .where(FitFoodDietCategorySpecification.checkUseYn(fitFoodDietCategory.getUseYn()));

        return fitFoodDietCategoryRepository.findAll(specification);

    }

    @Override
    public List<FitFoodDietFood> findAll(FitFoodDietFood fitFoodDietFood) {

        return fitFoodDietFoodRepository.findAll();

    }

    @Override
    public Optional<FitFoodDietFood> findOne(FitFoodDietFood fitFoodDietFood) {

        Specification<FitFoodDietFood> specification = Specification
                .where(FitFoodDietFoodSpecification.selectFood(fitFoodDietFood.getFoodNo()));

        return fitFoodDietFoodRepository.findOne(specification);

    }

}
