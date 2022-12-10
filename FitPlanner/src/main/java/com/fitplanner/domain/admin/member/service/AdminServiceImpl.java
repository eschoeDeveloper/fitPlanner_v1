package com.fitplanner.domain.admin.member.service;

import com.fitplanner.domain.admin.member.model.Admin;
import com.fitplanner.domain.admin.member.repository.AdminRepository;
import com.fitplanner.domain.admin.member.repository.specification.AdminSpecification;
import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.repository.MemberRepository;
import com.fitplanner.domain.member.repository.specification.MemberSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * package     : com.fitplanner.domain.member.service.impl
 * file        : MemberServiceImpl
 * author      : choeuiseung
 * date        : 2022/10/22
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/22                choeuiseung                 최초 생성
 * =======================================================
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Admin> findAdmin(int adminSeq) {
        return adminRepository.findById(adminSeq);
    }

    @Override
    public Optional<Admin> findAdmin(String checkId) {

        Specification<Admin> specification = Specification
                .where(AdminSpecification.selectAdminId(checkId))
                .and(AdminSpecification.activeAdminId());

        return adminRepository.findOne(specification);
    }

    @Override
    public int loginAdmin(Admin findAdmin) {

        int memberSeq = 0;

        String findId = findAdmin.getId();
        String checkPassword = findAdmin.getPassword();

        Optional<Admin> loginAdmin = adminRepository.loginAdmin(findId);

        if(loginAdmin.isPresent()) {

//            log.info("matches = {}", loginMember.toString());
//            log.info("matches = {}", passwordEncoder.matches(checkPassword, loginMember.get().getPassword()));

            if (passwordEncoder.matches(checkPassword, loginAdmin.get().getPassword())) {
                memberSeq = loginAdmin.get().getSeq();
            }

        }

        return memberSeq;

    }

    @Override
    public int createAdmin(Admin signUpAdmin) {

        int complete = 0;

        Optional<Admin> checkAdmin = Optional.ofNullable(signUpAdmin);

        if(checkAdmin.isPresent()) {

            String encodePassword = passwordEncoder.encode(signUpAdmin.getPassword());
            signUpAdmin.setPassword(encodePassword);
            signUpAdmin.setRoleSeq(3); // 일반관리자 셋팅

            log.info("signUpMember = {}, {}", signUpAdmin.getId(), signUpAdmin.getEmail());

            complete = adminRepository.save(signUpAdmin).getSeq();

        }

        return complete;

    }

    @Override
    public int updateAdmin(Admin updateAdmin) {
        return adminRepository.save(updateAdmin).getSeq();
    }

    @Override
    public int deleteAdmin(Admin deleteAdmin) {
        return adminRepository.deleteAdmin(deleteAdmin.getId());
    }

    @Override
    public int resetPassword(Admin updateAdmin) {
        return adminRepository.resetPassword(updateAdmin.getId(), updateAdmin.getPassword());
    }

}



