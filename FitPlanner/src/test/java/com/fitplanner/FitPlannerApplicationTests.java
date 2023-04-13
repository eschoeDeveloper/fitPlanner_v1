package com.fitplanner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class FitPlannerApplicationTests {

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String encodePassword = passwordEncoder.encode("!@mkd2ek91");
        System.out.println(encodePassword);
    }

}
