package com.fitplanner.domain.fitSchedule.repository.specification;

import com.fitplanner.domain.fitSchedule.model.FitSchedule;
import org.springframework.data.jpa.domain.Specification;

public class FitScheduleSpecification {

    public static Specification<FitSchedule> betweenDate(String fromDate, String toDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("scheduleDt"), fromDate, toDate);
    }

    public static Specification<FitSchedule> matchedMember(int memberSeq) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("memberSeq"), memberSeq);
    }

    public static Specification<FitSchedule> selectScheduleNo(int scheduleNo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("scheduleNo"), scheduleNo);
    }

}
