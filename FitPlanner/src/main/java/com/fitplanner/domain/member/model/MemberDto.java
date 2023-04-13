package com.fitplanner.domain.member.model;

import lombok.Builder;
import lombok.Data;

/**
 * package     : com.fitplanner.domain.member.model
 * file        : MemberDto
 * author      : choeuiseung
 * date        : 2022/10/23
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/23                choeuiseung                 최초 생성
 * =======================================================
 */
@Data
public class MemberDto {

    private int seq;
    private String id;
    private String name;
    private String phone;
    private String age;
    private String gender;
    private String birthday;
    private String email;
    private int roleSeq;
    private String roleNm;
    private int roleLevel;

    @Builder
    public MemberDto(int seq, String id, String name, String phone, String age, String gender, String birthday, String email, int roleSeq, String roleNm, int roleLevel) {

        this.seq = seq;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.roleSeq = roleSeq;
        this.roleNm = roleNm;
        this.roleLevel = roleLevel;

    }

}
