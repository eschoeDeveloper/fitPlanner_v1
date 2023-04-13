package com.fitplanner.domain.admin.member.service;

import com.fitplanner.domain.admin.member.model.AdminMember;
import com.fitplanner.domain.admin.member.repository.AdminMemberRepository;
import com.fitplanner.domain.admin.member.repository.specification.AdminMemberSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * package     : com.fitplanner.domain.member.service.impl
 * file        : MemberServiceImpl
 * author      : choeuiseung
 * date        : 2022/10/22
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/22                choeuiseung                 최초 생성
 * =======================================================
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMemberServiceImpl implements AdminMemberService {

    private final AdminMemberRepository adminMemberRepository;

    @Override
    public List<AdminMember> memberList() {
        Specification<AdminMember> specification = Specification
                .where(AdminMemberSpecification.memberRole());

        return adminMemberRepository.findAll(specification);
    }

    @Override
    public Optional<AdminMember> findMember(int memberSeq) {

        log.info("memberSeq :: " + memberSeq);

        Specification<AdminMember> specification = Specification
                .where(AdminMemberSpecification.selectMember(memberSeq));

        return adminMemberRepository.findOne(specification);

    }

    @Override
    public int updateMember(AdminMember updateMember) {
        return adminMemberRepository.save(updateMember).getSeq();
    }

    @Override
    public int deleteMember(AdminMember deleteMember) {
        return adminMemberRepository.deleteMember(deleteMember.getId());
    }

    @Override
    public int resetPassword(AdminMember updateMember) {
        return adminMemberRepository.resetPassword(updateMember.getId(), updateMember.getPassword());
    }

}



