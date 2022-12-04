package com.fitplanner.domain.fitSchedule.repository;

import com.fitplanner.domain.fitSchedule.model.FitSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FitScheduleRepository extends JpaRepository<FitSchedule, Object>, JpaSpecificationExecutor<FitSchedule> {
}
