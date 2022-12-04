package com.fitplanner.domain.member.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity(name="FP_MEMBER")
@NoArgsConstructor
// Exclude 어노테이션이 명시된 컬럼만 표시
@ToString(onlyExplicitlyIncluded = true)
@DynamicUpdate
public class Member {

    @ManyToOne(targetEntity = MemberRole.class, fetch = FetchType.EAGER)
    @JoinColumns(value = {
       @JoinColumn(name="ROLE_SEQ", referencedColumnName = "ROLE_SEQ", insertable = false, updatable = false)
    })
    private MemberRole memberRole;

    @Id
    @Column(name="SEQ", insertable=false, updatable=false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer seq;

    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String name;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="PHONE")
    private String phone;

    @Column(name="GENDER")
    private String gender;

    @Column(name="AGE")
    private String age;

    @Column(name="BIRTHDAY")
    private String birthday;

    @Column(name="EMAIL")
    private String email;

    @Column(name="ROLE_SEQ")
    private int roleSeq;

    @Column(name="REGIST_ID")
    private String registId;

    @Column(name="REGIST_DT")
    private String registDt;

    @Column(name="UPDATE_ID")
    private String updateId;

    @Column(name="UPDATE_DT")
    private String updateDt;

    @Builder
    public Member(String id, String name, String password, String phone, String gender, String age, String registId, String updateId) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.registId = registId;
        this.updateId = updateId;

    }

}
