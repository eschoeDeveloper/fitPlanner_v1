package com.fitplanner.domain.admin.member.service;

import com.fitplanner.domain.admin.member.model.Admin;
import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;

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
public interface AdminService {

    Optional<Admin> findAdmin(int adminSeq);
    Optional<Admin> findAdmin(String checkId);

    int loginAdmin(Admin findAdmin);
    int createAdmin(Admin createAdmin);
    int updateAdmin(Admin updateAdmin);
    int deleteAdmin(Admin deleteAdmin);
    int resetPassword(Admin updateAdmin);

}
