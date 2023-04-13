package com.fitplanner.domain.fitFoodDiet.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * package     : com.fitplanner.domain.fitFoodDiet.model
 * file        : FitFoodDietCategory
 * author      : choeuiseung
 * date        : 2022/11/05
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/11/05                choeuiseung                 최초 생성
 * =======================================================
 */
@Data
@Entity(name = "FIT_FOODDIET_CATEGORY")
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class FitFoodDietCategory {

    @Id
    @Column(name = "CATEGORY_NO")
    private int categoryNo;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "useYn")
    private String useYn;

    @Column(name = "REGIST_ID")
    private String registId;

    @Column(name = "REGIST_DT")
    private String registDt;

}
