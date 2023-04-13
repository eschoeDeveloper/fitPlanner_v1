package com.fitplanner.domain.fitSchedule.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity(name="FP_EXERCISE_SCHEDULE")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class FitSchedule {

    @Id
    @Column(name = "SCHEDULE_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduleNo;

    @Column(name = "MEMBER_SEQ")
    private int memberSeq;

    @Column(name = "SCHEDULE_TITLE")
    private String scheduleTitle;

    @Column(name = "SCHEDULE_DESC")
    private String scheduleDesc;

    @Column(name = "SCHEDULE_DT")
    private String scheduleDt;

    @Column(name = "REGIST_DT")
    private String registDt;

    @Transient
    private String fromDt;

    @Transient
    private String toDt;

    @Transient
    private int contentSize;

}
