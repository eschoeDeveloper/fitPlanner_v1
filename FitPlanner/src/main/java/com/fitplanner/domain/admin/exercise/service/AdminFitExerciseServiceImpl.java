package com.fitplanner.domain.admin.exercise.service;

import com.fitplanner.domain.admin.exercise.model.AdminFitExercise;
import com.fitplanner.domain.admin.exercise.model.AdminFitExerciseCategory;
import com.fitplanner.domain.admin.exercise.repository.AdminFitExerciseCategoryRepository;
import com.fitplanner.domain.admin.exercise.repository.AdminFitExerciseRepository;
import com.fitplanner.domain.admin.exercise.repository.specification.FitExerciseSpecification;
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
public class AdminFitExerciseServiceImpl implements AdminFitExerciseService {

    private final AdminFitExerciseRepository fitExerciseRepository;

    private final AdminFitExerciseCategoryRepository fitExerciseCategoryRepository;

    @Override
    public List<AdminFitExerciseCategory> getExerciseCategoryList() {
        return fitExerciseCategoryRepository.findAll();
    }

    @Override
    public List<AdminFitExercise> getExerciseList(AdminFitExercise fitExercise) {
        return fitExerciseRepository.findAll();
    }

    @Override
    public Optional<AdminFitExercise> getExerciseInfo(AdminFitExercise fitExercise) {

        Specification<AdminFitExercise> specification = Specification
                .where(FitExerciseSpecification.selectExercise(fitExercise.getExerciseNo()));

        return fitExerciseRepository.findOne(specification);

    }
}
