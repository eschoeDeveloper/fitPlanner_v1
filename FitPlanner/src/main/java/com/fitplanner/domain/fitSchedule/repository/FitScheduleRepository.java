package com.fitplanner.domain.fitSchedule.repository;

import com.fitplanner.domain.fitSchedule.model.FitSchedule;
import com.fitplanner.domain.fitSchedule.model.FitSchedulePages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FitScheduleRepository extends JpaRepository<FitSchedule, Object>, JpaSpecificationExecutor<FitSchedule> {

    @Query(nativeQuery = true, value = "SELECT * FROM ( SELECT SCHEDULE_NO as scheduleNo, LAG(SCHEDULE_NO, 1, 0) OVER(ORDER BY SCHEDULE_NO) AS prevNo, LEAD(SCHEDULE_NO, 1, 0) OVER(ORDER BY SCHEDULE_NO) AS nextNo FROM FP_EXERCISE_SCHEDULE ) TBL WHERE scheduleNo = :scheduleNo\n")
    Optional<FitSchedulePages> fitSchedulePages(int scheduleNo);

}
