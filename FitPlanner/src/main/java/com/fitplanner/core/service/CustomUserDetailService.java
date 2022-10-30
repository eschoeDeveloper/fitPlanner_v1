package com.fitplanner.core.service;

import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;

/**
 * package     : com.fitplanner.core.service
 * file        : CustomUserDetailService
 * author      : choeuiseung
 * date        : 2022/10/30
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/30                choeuiseung                 최초 생성
 * =======================================================
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String checkId) throws UsernameNotFoundException {

        return memberRepository.findById(checkId)
                .map(this::findMember)
                .orElseThrow(EntityNotFoundException::new);
    }

    private UserDetails findMember(Member findMember) {

        String roleNm = findMember.getMemberRole().getRoleNm();

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleNm);

        return User.builder()
                .username(findMember.getId())
                .password(findMember.getPassword())
                .authorities(authority)
                .build();

    }
}
