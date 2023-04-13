package com.fitplanner.domain.admin.admin.service;

import com.fitplanner.domain.admin.admin.model.Admin;

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

    List<Admin> adminList();
    List<Admin> memberList();

    int loginAdmin(Admin findAdmin);
    int createAdmin(Admin createAdmin);
    int updateAdmin(Admin updateAdmin);
    int deleteAdmin(Admin deleteAdmin);
    int resetPassword(Admin updateAdmin);

}
