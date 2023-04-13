package com.fitplanner.domain.main.repository;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository("memberMainRepository")
public class MemberMainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> getFitScheduleList(int memberSeq) {

        String sqlQuery = "SELECT\n" +
                "*\n" +
                "FROM FP_EXERCISE_SCHEDULE a\n" +
                "WHERE a.MEMBER_SEQ = "+ memberSeq +"\n" +
                "ORDER BY a.SCHEDULE_NO DESC\n" +
                "LIMIT 0,5;";

        Query query = entityManager.createNativeQuery(sqlQuery);

        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();

    }

    public List<Map<String, Object>> getFoodDietList(int memberSeq) {

        String sqlQuery = "SELECT\n" +
                "a.*\n" +
                "FROM FP_FOODDIET_FOOD a\n" +
                "JOIN FP_FOODDIET_MEMBER b\n" +
                "WHERE b.FOOD_IDX LIKE '%' || a.FOOD_NO || '%' " +
                "  AND b.MEMBER_SEQ = "+ memberSeq +"\n" +
                "ORDER BY a.FOOD_NO DESC\n" +
                "LIMIT 0,5;";

        Query query = entityManager.createNativeQuery(sqlQuery);

        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();

    }

}
