package com.fitplanner.domain.fitSchedule.service;

import com.fitplanner.domain.fitSchedule.model.FitSchedule;

import java.util.List;
import java.util.Optional;

public interface FitScheduleService {

    List<FitSchedule> getFitScheduleList(FitSchedule fitSchedule);

    Optional<FitSchedule> getFitScheduleInfo(FitSchedule fitSchedule);

    int createFitSchedule(FitSchedule fitSchedule);

}
