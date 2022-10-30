package com.fitplanner.domain.member.service;

import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.model.MemberDto;
import com.fitplanner.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Member findById(int seq) {

        String parseSeq = String.valueOf(seq);
        return memberRepository.findById(String.valueOf(parseSeq)).orElse(null);

    }

    @Override
    public List<CheckIdMapping> findById(String checkId) {
        return memberRepository.findMemberById(checkId);
    }

    @Override
    public int loginMember(Member loginMember) {

        String loginId = loginMember.getId();
        String loginPassword = loginMember.getPassword();

        List<CheckIdMapping> findMember = memberRepository.findMemberById(loginId);

        int isLogin = 0;

        log.info("matches = {}" , passwordEncoder.matches(loginPassword, findMember.get(0).getPassword()));

        if (passwordEncoder.matches(loginPassword, findMember.get(0).getPassword())){
            isLogin = 1;
        }

        return isLogin;

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

}



