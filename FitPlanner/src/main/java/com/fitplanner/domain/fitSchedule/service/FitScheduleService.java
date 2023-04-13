package com.fitplanner.domain.fitSchedule.service;

import com.fitplanner.domain.fitSchedule.model.FitSchedule;
import com.fitplanner.domain.fitSchedule.model.FitSchedulePages;

import java.util.List;
import java.util.Optional;

public interface FitScheduleService {

    List<FitSchedule> getFitScheduleList(FitSchedule fitSchedule);

    Optional<FitSchedule> getFitScheduleInfo(FitSchedule fitSchedule);

    Optional<FitSchedulePages> getFitSchedulePages(int scheduleNo);

    int createFitSchedule(FitSchedule fitSchedule);

}
