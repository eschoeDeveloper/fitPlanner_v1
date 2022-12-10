package com.fitplanner.domain.fitFoodDiet.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity(name = "FP_FOODDIET_MEMBER")
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class FitFoodDietMember {

    @Id
    @Column(name = "SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seq;

    @Column(name = "FOOD_IDX")
    private String foodIdx;

    @Column(name = "MEMBER_SEQ")
    private int memberSeq;

    @Column(name = "REGIST_ID")
    private String registId;

    @Column(name = "REGIST_DT")
    private String registDt;

}
