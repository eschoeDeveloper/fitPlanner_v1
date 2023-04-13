package com.fitplanner.core.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fitplanner.domain.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class UserDetailsModel implements UserDetails {

    private int userSeq;

    private String userId;

    @JsonIgnore
    private String userPassword;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsModel build(Member member) {

        String getRoleLevel = String.valueOf( member.getMemberRole().getRoleLevel() );

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(getRoleLevel));

        return new UserDetailsModel(
            member.getSeq(),
            member.getId(),
            member.getPassword(),
            authorities
        );

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public int getUserSeq() {
        return userSeq;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsModel user = (UserDetailsModel) o;
        return Objects.equals(userId, user.userId);
    }

}
