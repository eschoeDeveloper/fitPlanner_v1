package com.fitplanner.domain.fitSchedule.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

public interface FitSchedulePages {

    Integer getScheduleNo();
    Integer getPrevNo();
    Integer getNextNo();

}
