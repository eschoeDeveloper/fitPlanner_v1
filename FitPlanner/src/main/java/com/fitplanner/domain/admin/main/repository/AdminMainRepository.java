package com.fitplanner.domain.admin.main.repository;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository("adminMainRepository")
public class AdminMainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> getExerciseRankList() {

        String sqlQuery = "SELECT * FROM ( \n" +
                "\tSELECT\n" +
                "\t   b.category_no exerciseCategoryNo,\n" +
                "\t   b.category_nm exerciseCategoryNm,\n" +
                "\t   COUNT(b.category_no) AS exerciseScore,\n" +
                "\t   RANK() OVER (\n" +
                "\t\t ORDER BY COUNT(b.category_no) desc, b.category_nm desc\n" +
                "\t   ) exerciseRank\n" +
                "\tFROM FP_EXERCISE a, FP_EXERCISE_CATEGORY b\n" +
                "\tWHERE a.category_IDX LIKE '%' || b.category_no || '%'\n" +
                "\tGROUP BY b.category_no\n" +
                ") TBL\n" +
                "WHERE TBL.exerciseScore > 0 AND TBL.exerciseScore <= 5\n";

        Query query = entityManager.createNativeQuery(sqlQuery);

        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();

    }

    public List<Map<String, Object>> getMemberAgeList() {

        String sqlQuery = "SELECT \n" +
                "\t*\n" +
                "FROM (\n" +
                "\n" +
                "\tWITH AGE_RANGE AS (\n" +
                "\t\tSELECT\n" +
                "\t\t10 as 'a',\n" +
                "\t\t20 as 'b',\n" +
                "\t\t30 as 'c',\n" +
                "\t\t40 as 'd',\n" +
                "\t\t50 as 'e',\n" +
                "\t\t60 as 'f',\n" +
                "\t\t70 as 'g',\n" +
                "\t\t80 as 'h',\n" +
                "\t\t90 as 'i',\n" +
                "\t\t100 as 'j',\n" +
                "\t\t110 as 'k',\n" +
                "\t\t120 as 'l'\n" +
                "\t) SELECT\n" +
                "\t   SUM(case when(age >= a.a AND age < a.b) THEN 1 ELSE 0 END) as '10대',\n" +
                "\t   SUM(case when(age >= a.b AND age < a.c) THEN 1 ELSE 0 END) as '20대',\n" +
                "\t   SUM(case when(age >= a.c AND age < a.d) THEN 1 ELSE 0 END) as '30대',\n" +
                "\t   SUM(case when(age >= a.d AND age < a.e) THEN 1 ELSE 0 END) as '40대',\n" +
                "\t   SUM(case when(age >= a.e AND age < a.f) THEN 1 ELSE 0 END) as '50대',\n" +
                "\t   SUM(case when(age >= a.f AND age < a.g) THEN 1 ELSE 0 END) as '60대',\n" +
                "\t   SUM(case when(age >= a.g AND age < a.h) THEN 1 ELSE 0 END) as '70대',\n" +
                "\t   SUM(case when(age >= a.h AND age < a.i) THEN 1 ELSE 0 END) as '80대',\n" +
                "\t   SUM(case when(age >= a.i AND age < a.j) THEN 1 ELSE 0 END) as '90대',\n" +
                "\t   SUM(case when(age >= a.j AND age < a.k) THEN 1 ELSE 0 END) as '100대',\n" +
                "\t   SUM(case when(age >= a.k AND age < a.l) THEN 1 ELSE 0 END) as '110대',\n" +
                "\t   SUM(case when(age >= a.l AND age < 130) THEN 1 ELSE 0 END) as '120대'\n" +
                "\tFROM AGE_RANGE a, (\n" +
                "\t\tSELECT\n" +
                "\t\t  strftime('%Y', 'now') - substr(birthday, 1, 4) - (strftime('%m-%d', 'now') < substr(birthday, 6)) age\n" +
                "\t\tFROM FP_MEMBER\n" +
                "\t) b\n" +
                "\tGROUP BY a.a, a.b, a.c, a.d, a.e, a.f, a.g, a.h, a.i, a.j, a.k, a.l\n" +
                "\n" +
                ")";

        Query query = entityManager.createNativeQuery(sqlQuery);

        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();

    }

    public List<Map<String, Object>> getMemberSexList() {

        String sqlQuery = "SELECT\n" +
                "\tSUM( CASE WHEN (b.CODE_NM = '남성') THEN 1 ELSE 0 END ) AS G01,\n" +
                "\tSUM( CASE WHEN (b.CODE_NM = '여성') THEN 1 ELSE 0 END ) AS G02\n" +
                "FROM FP_MEMBER a\n" +
                "JOIN FP_CODE b\n" +
                "ON a.gender = b.CODE_ID\n" +
                "GROUP BY b.CODE_ID";

        Query query = entityManager.createNativeQuery(sqlQuery);

        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();

    }

}
