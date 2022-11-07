package com.fitplanner.domain.member.service;

import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.model.MemberDto;

import java.util.List;
import java.util.Optional;

/**
 * package     : com.fitplanner.domain.member.service
 * file        : MemberService
 * author      : choeuiseung
 * date        : 2022/10/22
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/22                choeuiseung                 최초 생성
 * =======================================================
 */
public interface MemberService {

    List<Member> findAll();
    Optional<Member> findById(int seq);
    List<CheckIdMapping> findById(String checkId);

    int loginMember(Member findMember);
    int signUpMember(Member insertMember);

    int updateMember(Member updateMember);

}
