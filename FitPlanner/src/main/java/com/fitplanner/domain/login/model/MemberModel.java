package com.fitplanner.domain.login.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
// Exclude 어노테이션이 명시된 컬럼만 표시
@ToString(onlyExplicitlyIncluded = true)
public class MemberModel {

    // 해당 어노테이션을 붙이면 ToString 로그에 표시
    @ToString.Exclude
    public String loginId;

    @ToString.Exclude
    public String loginPwd;


}
