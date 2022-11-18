package com.fitplanner.core.security;

import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.loginMember(userId)
                .orElseThrow(() -> new UsernameNotFoundException("로그인한 사용자를 찾을 수 없습니다. {} " + userId));

        return UserDetailsModel.build(member);
    }

}
