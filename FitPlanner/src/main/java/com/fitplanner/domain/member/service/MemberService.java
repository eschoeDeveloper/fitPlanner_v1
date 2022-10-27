package com.fitplanner.domain.member.service;

import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.model.MemberDto;

import java.util.List;

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
    Member findById(int seq);
    List<CheckIdMapping> findById(String checkId);

    MemberDto loginMember(Member findMember);
    int signUpMember(Member insertMember);

}
