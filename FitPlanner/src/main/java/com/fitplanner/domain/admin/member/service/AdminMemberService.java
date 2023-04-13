package com.fitplanner.domain.admin.member.service;

import com.fitplanner.domain.admin.member.model.AdminMember;

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
public interface AdminMemberService {

    Optional<AdminMember> findMember(int adminSeq);

    List<AdminMember> memberList();

    int updateMember(AdminMember updateAdmin);
    int deleteMember(AdminMember deleteAdmin);

    int resetPassword(AdminMember updateAdmin);

}
