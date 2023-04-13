package com.fitplanner.domain.admin.member.repository.specification;

import com.fitplanner.domain.admin.member.model.AdminMember;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminMemberSpecification {

    public static Specification<AdminMember> selectMember(int memberSeq) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("seq"), memberSeq);
    }

    public static Specification<AdminMember> memberRole() {

        List<Predicate> predicates = new ArrayList<>();

        List<String> roleSeqList = Arrays.asList("4,5".split(","));

        return (root, query, criteriaBuilder) -> {
            predicates.add(root.get("roleSeq").in(roleSeqList));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }

}
