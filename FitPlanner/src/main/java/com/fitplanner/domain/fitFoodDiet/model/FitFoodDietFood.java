package com.fitplanner.domain.fitFoodDiet.model;

import lombok.*;

import javax.persistence.*;

/**
 * package     : com.fitplanner.domain.fitFoodDiet.model
 * file        : FitFoodDietFood
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
@Entity(name = "FP_FOODDIET_FOOD")
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class FitFoodDietFood {

    @Id
    @Column(name = "FOOD_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int foodNo;

    @Column(name = "CATEGORY_NO")
    private int categoryNo;

    @Column(name = "FOOD")
    private String food;

    @Column(name = "INTAKE")
    private String intake;

    @Column(name = "REGIST_ID")
    private String registId;

    @Column(name = "REGIST_DT")
    private String registDt;

}
