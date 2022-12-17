package com.fitplanner.domain.admin.member.repository;

import com.fitplanner.domain.admin.member.model.AdminMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * package     : com.fitplanner.domain.member.repository
 * file        : MemberRepository
 * author      : choeuiseung
 * date        : 2022/10/22
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/22                choeuiseung                 최초 생성
 * =======================================================
 */
public interface AdminMemberRepository extends JpaRepository<AdminMember, Object>, JpaSpecificationExecutor<AdminMember> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE FP_MEMBER SET PASSWORD = :newPassword WHERE ID = :id AND ROLE_SEQ < 4  AND DEL_YN = 'N'")
    int resetPassword(@Param("id") String id, @Param("newPassword") String newPassword);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="DELETE FROM FP_MEMBER WHERE ID = :id")
    int deleteMember(@Param("id") String id);

}
