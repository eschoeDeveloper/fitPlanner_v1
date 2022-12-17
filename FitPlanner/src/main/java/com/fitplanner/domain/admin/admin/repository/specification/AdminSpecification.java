package com.fitplanner.domain.admin.admin.repository.specification;

import com.fitplanner.domain.admin.admin.model.Admin;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminSpecification {

    public static Specification<Admin> selectAdminId(String checkId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), checkId);
    }

    public static Specification<Admin> activeAdminId() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("delYn"), "N");
    }

    public static Specification<Admin> adminRole() {

        List<Predicate> predicates = new ArrayList<>();

        List<String> roleSeqList = Arrays.asList("2,3".split(","));

        return (root, query, criteriaBuilder) -> {
            predicates.add(root.get("roleSeq").in(roleSeqList));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }

    public static Specification<Admin> memberRole() {

        List<Predicate> predicates = new ArrayList<>();

        List<String> roleSeqList = Arrays.asList("4,5".split(","));

        return (root, query, criteriaBuilder) -> {
            predicates.add(root.get("roleSeq").in(roleSeqList));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }

}
