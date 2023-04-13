package com.fitplanner.domain.member.repository.specification;

import com.fitplanner.domain.member.model.Member;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpecification {

    public static Specification<Member> checkEmail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
    }

}
