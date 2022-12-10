package com.fitplanner.domain.fitFoodDiet.service;

import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietCategory;
import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietFood;
import com.fitplanner.domain.fitFoodDiet.model.FitFoodDietMember;
import com.fitplanner.domain.fitFoodDiet.repository.FitFoodDietCategoryRepository;
import com.fitplanner.domain.fitFoodDiet.repository.FitFoodDietFoodRepository;
import com.fitplanner.domain.fitFoodDiet.repository.FitFoodDietMemberRepository;
import com.fitplanner.domain.fitFoodDiet.repository.specification.FitFoodDietCategorySpecification;
import com.fitplanner.domain.fitFoodDiet.repository.specification.FitFoodDietFoodSpecification;
import com.fitplanner.domain.fitFoodDiet.repository.specification.FitFoodDietMemberSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    private final FitFoodDietMemberRepository fitFoodDietMemberRepository;

    @Override
    public List<FitFoodDietCategory> getCategoryList(FitFoodDietCategory fitFoodDietCategory) {

        Specification<FitFoodDietCategory> specification = Specification
                .where(FitFoodDietCategorySpecification.checkUseYn(fitFoodDietCategory.getUseYn()));

        return fitFoodDietCategoryRepository.findAll(specification);

    }

    @Override
    public List<FitFoodDietFood> findAll(FitFoodDietFood fitFoodDietFood, int memberSeq) {

        List<FitFoodDietFood> findList = Collections.emptyList();

        Specification<FitFoodDietMember> specification = Specification
                .where(FitFoodDietMemberSpecification.selectMemberSeq(memberSeq));

        Optional<FitFoodDietMember> fitFoodDietMember = fitFoodDietMemberRepository.findOne(specification);

        if(fitFoodDietMember.isPresent()) {

            FitFoodDietMember obj = fitFoodDietMember.get();

            Specification<FitFoodDietFood> foodSpecification = Specification
                    .where(FitFoodDietFoodSpecification.selectFoodMulti(obj.getFoodIdx()));

            findList = fitFoodDietFoodRepository.findAll(foodSpecification);

        }

        return findList;

    }

    @Override
    public Optional<FitFoodDietFood> findOne(FitFoodDietFood fitFoodDietFood) {

        Specification<FitFoodDietFood> specification = Specification
                .where(FitFoodDietFoodSpecification.selectFood(fitFoodDietFood.getFoodNo()));

        return fitFoodDietFoodRepository.findOne(specification);

    }

}
