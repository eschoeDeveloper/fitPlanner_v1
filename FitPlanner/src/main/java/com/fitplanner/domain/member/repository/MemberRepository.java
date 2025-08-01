package com.fitplanner.domain.member.repository;

import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
public interface MemberRepository extends JpaRepository<Member, Object>, JpaSpecificationExecutor<Member> {

    @Query(nativeQuery = true, value="SELECT a.SEQ, a.ID, a.NAME, a.PASSWORD, a.PHONE, a.BIRTHDAY, a.EMAIL, a.AGE, a.GENDER, a.DEL_YN, a.REGIST_ID, a.REGIST_DT, a.UPDATE_ID, a.UPDATE_DT, b.ROLE_SEQ, b.ROLE_NM, b.ROLE_LEVEL FROM FP_MEMBER a, FP_ROLE b WHERE a.ROLE_SEQ = b.ROLE_SEQ AND a.ID = :checkId AND a.DEL_YN = 'N'")
    Optional<Member> loginMember(@Param("checkId") String checkId);

//    @Query(nativeQuery = true, value="SELECT a.SEQ, a.ID, a.NAME, a.PASSWORD, a.PHONE, a.BIRTHDAY, a.EMAIL, a.AGE, a.GENDER, a.REGIST_ID, a.REGIST_DT, a.UPDATE_ID, a.UPDATE_DT, b.ROLE_SEQ, b.ROLE_NM, b.ROLE_LEVEL FROM FP_MEMBER a, FP_ROLE b WHERE a.ROLE_SEQ = b.ROLE_SEQ AND a.ID = :id AND a.PASSWORD = :password")
//    Member loginMember(@Param("id") String id, @Param("password") String password);

    @Query(nativeQuery = true, value="SELECT a.SEQ, a.PASSWORD FROM FP_MEMBER a WHERE ID = :checkId")
    List<CheckIdMapping> findMemberById(@Param("checkId") String checkId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE FP_MEMBER SET PASSWORD = :newPassword WHERE ID = :id AND DEL_YN = 'N'")
    int resetPassword(@Param("id") String id, @Param("newPassword") String newPassword);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE FP_MEMBER SET DEL_YN = 'Y' WHERE ID = :id AND DEL_YN = 'N'")
    int deleteMember(@Param("id") String id);

}
