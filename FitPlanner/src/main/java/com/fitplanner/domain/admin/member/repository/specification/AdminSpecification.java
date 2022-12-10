package com.fitplanner.domain.admin.member.repository.specification;

import com.fitplanner.domain.admin.member.model.Admin;
import org.springframework.data.jpa.domain.Specification;

public class AdminSpecification {

    public static Specification<Admin> selectAdminId(String checkId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), checkId);
    }

    public static Specification<Admin> activeAdminId() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("delYn"), "N");
    }

}
