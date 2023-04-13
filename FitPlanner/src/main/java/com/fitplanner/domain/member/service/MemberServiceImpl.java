package com.fitplanner.domain.member.service;

import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.repository.MemberRepository;
import com.fitplanner.domain.member.repository.specification.MemberSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findById(int seq) {
        Optional<Member> findMember = memberRepository.findById(seq);
        return findMember;
    }

    @Override
    public Optional<Member> checkEmail(String email) {
        Specification<Member> specification = Specification.where(MemberSpecification.checkEmail(email));
        return memberRepository.findOne(specification);
    }

    @Override
    public List<CheckIdMapping> findById(String checkId) {
        return memberRepository.findMemberById(checkId);
    }

    @Override
    public int loginMember(Member findMember) {

        int memberSeq = 0;

        String findId = findMember.getId();
        String checkPassword = findMember.getPassword();

        Optional<Member> loginMember = memberRepository.loginMember(findId);

        if(loginMember.isPresent()) {

//            log.info("matches = {}", loginMember.toString());
//            log.info("matches = {}", passwordEncoder.matches(checkPassword, loginMember.get().getPassword()));

            if (passwordEncoder.matches(checkPassword, loginMember.get().getPassword())) {
                memberSeq = loginMember.get().getSeq();
            }

        }

        return memberSeq;

    }

    @Override
    public int signUpMember(Member signUpMember) {

        int complete = 0;

        Optional<Member> checkMember = Optional.ofNullable(signUpMember);

        if(checkMember.isPresent()) {

            String encodePassword = passwordEncoder.encode(signUpMember.getPassword());
            signUpMember.setPassword(encodePassword);
            signUpMember.setRoleSeq(5); // 일반사용자 셋팅

            log.info("signUpMember = {}, {}", signUpMember.getId(), signUpMember.getEmail());

            complete = memberRepository.save(signUpMember).getSeq();

        }

        return complete;

    }

    @Override
    public int updateMember(Member updateMember) {
        return memberRepository.save(updateMember).getSeq();
    }

    @Override
    public int deleteMember(Member deleteMember) {
        return memberRepository.deleteMember(deleteMember.getId());
    }

    @Override
    public int resetPassword(Member updateMember) {
        return memberRepository.resetPassword(updateMember.getId(), updateMember.getPassword());
    }

}



