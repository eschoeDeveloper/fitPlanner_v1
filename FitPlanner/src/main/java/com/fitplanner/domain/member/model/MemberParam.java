package com.fitplanner.domain.member.model;

import lombok.Data;

/**
 * package     : com.fitplanner.domain.member.model
 * file        : MemberParam
 * author      : choeuiseung
 * date        : 2022/10/24
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/24                choeuiseung                 최초 생성
 * =======================================================
 */
@Data
public class MemberParam {

    private String id;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String age;
    private String gender;
    private String birthday;

}
