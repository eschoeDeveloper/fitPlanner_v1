package com.fitplanner.domain.fitExercise.service;

import com.fitplanner.domain.fitExercise.model.FitExercise;
import com.fitplanner.domain.fitExercise.model.FitExerciseCategory;
import com.fitplanner.domain.fitExercise.repository.FitExerciseCategoryRepository;
import com.fitplanner.domain.fitExercise.repository.specification.FitExerciseCategorySpecification;
import com.fitplanner.domain.fitExercise.repository.FitExerciseRepository;
import com.fitplanner.domain.fitExercise.repository.specification.FitExerciseSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * package     : com.fitplanner.domain.fitExercise.service
 * file        : FitExerciseServiceImpl
 * author      : choeuiseung
 * date        : 2022/11/02
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/02                choeuiseung                 최초 생성
 * =======================================================
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FitExerciseServiceImpl implements FitExerciseService {

    private final FitExerciseRepository fitExerciseRepository;

    private final FitExerciseCategoryRepository fitExerciseCategoryRepository;

    @Override
    public List<FitExerciseCategory> getExerciseCategoryList() {

        final String useYn = "Y";
        Specification<FitExerciseCategory> specification = Specification.where(FitExerciseCategorySpecification.activeUseYn(useYn));

        return fitExerciseCategoryRepository.findAll(specification);

    }

    @Override
    public List<FitExercise> getExerciseList(FitExercise fitExercise) {

        Specification<FitExercise> specification = Specification
                .where(FitExerciseSpecification.checkUseYn(fitExercise.getUseYn()))
                .and(FitExerciseSpecification.selectCategory(fitExercise.getCategoryIdx()));

        return fitExerciseRepository.findAll(specification);

    }

    @Override
    public Optional<FitExercise> getExerciseInfo(FitExercise fitExercise) {

        Specification<FitExercise> specification = Specification
                .where(FitExerciseSpecification.selectExercise(fitExercise.getExerciseNo()))
                .and(FitExerciseSpecification.checkUseYn(fitExercise.getUseYn()));

        return fitExerciseRepository.findOne(specification);

    }
}
