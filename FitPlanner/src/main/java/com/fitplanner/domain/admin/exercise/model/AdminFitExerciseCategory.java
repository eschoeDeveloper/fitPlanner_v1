package com.fitplanner.domain.admin.exercise.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * package     : com.fitplanner.domain.fitExercise.model
 * file        : FitExerciseCategory
 * author      : choeuiseung
 * date        : 2022/11/02
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/02                choeuiseung                 최초 생성
 * =======================================================
 */
@Data
@Table(name = "FP_EXERCISE_CATEGORY")
@Entity
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class AdminFitExerciseCategory {

    @Id
    @Column(name = "CATEGORY_NO", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String categoryNo;

    @Column(name = "CATEGORY_NM")
    private String categoryNm;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "REGIST_DT")
    private String registDt;

}
