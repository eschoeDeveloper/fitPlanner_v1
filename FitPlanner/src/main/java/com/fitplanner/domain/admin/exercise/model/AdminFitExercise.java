package com.fitplanner.domain.admin.exercise.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * package     : com.fitplanner.domain.fitExercise.model
 * file        : FitExercise
 * author      : choeuiseung
 * date        : 2022/11/03
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/03                choeuiseung                 최초 생성
 * =======================================================
 */
@Data
@Table(name="FP_EXERCISE")
@Entity
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class AdminFitExercise {

    @Id
    @Column(name = "EXERCISE_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int exerciseNo;

    @Column(name = "CATEGORY_IDX")
    private String categoryIdx;

    @Column(name = "SUBCATEGORY_IDX")
    private String subcategoryIdx;

    @Column(name = "EXERCISE_NM")
    private String exerciseNm;

    @Column(name = "EXERCISE_DESC")
    private String exerciseDesc;

    @Column(name = "EXERCISE_IMAGE_PATH")
    private String exerciseImagePath;

    @Column(name = "EXERCISE_LEVEL")
    private String exerciseLevel;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "REGIST_DT")
    private String registDt;

}
