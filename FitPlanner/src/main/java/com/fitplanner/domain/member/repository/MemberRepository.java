package com.fitplanner.domain.member.repository;

import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
public interface MemberRepository extends JpaRepository<Member, Object> {

    @Query(nativeQuery = true, value="SELECT a.SEQ, a.ID, a.NAME, a.PASSWORD, a.PHONE, a.BIRTHDAY, a.EMAIL, a.AGE, a.GENDER, a.REGIST_ID, a.REGIST_DT, a.UPDATE_ID, a.UPDATE_DT, b.ROLE_SEQ, b.ROLE_NM, b.ROLE_LEVEL FROM FP_MEMBER a, FP_ROLE b WHERE a.ROLE_SEQ = b.ROLE_SEQ AND a.ID = :id AND a.PASSWORD = :password")
    Member loginMember(@Param("id") String id, @Param("password") String password);

    @Query(nativeQuery = true, value="SELECT a.SEQ, a.PASSWORD FROM FP_MEMBER a WHERE ID = :checkId")
    List<CheckIdMapping> findMemberById(@Param("checkId") String checkId);
//
//    @Query(nativeQuery = true, value="INSERT INTO FP_MEMBER(ID, NAME, PASSWORD, PHONE, BIRTHDAY, EMAIL, AGE, GENDER, ROLE_SEQ, REGIST_ID, REGIST_DT, UPDATE_ID, UPDATE_DT  ) SELECT :#{#signUpMember.id}, :#{#signUpMember.name}, :#{#signUpMember.password}, :#{#signUpMember.phone}, :#{#signUpMember.birthday}, :#{#signUpMember.email}, cast(strftime('%Y.%m%d', CURRENT_DATE) - strftime('%Y.%m%d', :#{#signUpMember.birthday}) AS TEXT) , :#{#signUpMember.gender}, ROLE_SEQ, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP  FROM FP_ROLE WHERE ROLE_NM = '일반회원'")
//    void signUpMember(@Param("signUpMember") Member signUpMember);

}
