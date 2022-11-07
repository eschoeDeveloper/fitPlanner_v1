package com.fitplanner.domain.memberRole;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * package     : com.fitplanner.domain.common.code
 * file        : MemberRole
 * author      : choeuiseung
 * date        : 2022/10/22
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/22                choeuiseung                 최초 생성
 * =======================================================
 */
@Data
@Entity(name="FP_ROLE")
@NoArgsConstructor
// Exclude 어노테이션이 명시된 컬럼만 표시
@ToString(onlyExplicitlyIncluded = true)
public class MemberRole {

    @Id
    @Column(name = "ROLE_SEQ")
    @JoinColumn(name = "ROLE_SEQ")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int roleSeq;

    @Column(name = "ROLE_NM")
    public String roleNm;

    @Column(name = "ROLE_LEVEL")
    public int roleLevel;

    @Column(name = "REGIST_DT")
    public int registDt;

    @Column(name = "registId")
    public int registId;

}
