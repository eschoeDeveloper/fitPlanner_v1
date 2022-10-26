package com.fitplanner.domain.member.service.impl;

import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.model.MemberDto;
import com.fitplanner.domain.member.repository.MemberRepository;
import com.fitplanner.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

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
    public MemberDto loginMember(Member findMember) {

        String loginId = findMember.getId();
        String loginPassword = findMember.getPassword();

        Member getMember = memberRepository.loginMember(loginId, loginPassword);

        MemberDto memberDto = MemberDto.builder()
                .seq(getMember.getSeq())
                .id(getMember.getId())
                .name(getMember.getName())
                .age(getMember.getAge())
                .gender(getMember.getGender())
                .phone(getMember.getPhone())
                .birthday(getMember.getBirthday())
                .email(getMember.getEmail())
                .roleLevel(getMember.getMemberRole().getRoleLevel())
                .roleNm(getMember.getMemberRole().getRoleNm())
                .roleSeq(getMember.getMemberRole().getRoleSeq())
                .build();

        return memberDto;

    }

}



