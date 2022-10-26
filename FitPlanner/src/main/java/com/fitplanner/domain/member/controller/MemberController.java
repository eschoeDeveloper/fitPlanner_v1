package com.fitplanner.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.model.MemberDto;
import com.fitplanner.domain.member.model.MemberParam;
import com.fitplanner.domain.member.service.MemberService;
import com.fitplanner.domain.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    MemberService memberService;

   @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ApiResponse> memberLogin(
           @RequestBody MemberParam memberParam,
           HttpServletRequest request
   ) {

       ApiResponse apiResponse;

       try {

           Member findMember = Member.builder().id(memberParam.getId()).password(memberParam.getPassword()).build();
           MemberDto loginMember = memberService.loginMember(findMember);

           HttpSession httpSession = request.getSession();

           //log.info("loginMember = {}", loginMember);

           ObjectMapper mapper = new ObjectMapper();
           String jsonString = mapper.writeValueAsString(loginMember);
           JSONObject jsonObject = new JSONObject(jsonString);

           if(httpSession.getAttribute("loginMember") == null) {
               httpSession.setAttribute("loginMember", loginMember);
           } else {

           }

           apiResponse = new ApiResponse();

           if(loginMember != null) {

               apiResponse.setMessage(HttpStatus.OK.name());
               apiResponse.setCode(HttpStatus.OK.value());
               apiResponse.setCount(1);
               apiResponse.setData(jsonObject.toString());

           } else {

               apiResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
               apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
               apiResponse.setCount(0);
               apiResponse.setData(Collections.emptyMap());

           }

       } catch(Exception e) {

           e.printStackTrace();

           apiResponse = new ApiResponse();

           apiResponse.setMessage(e.getLocalizedMessage());
           apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
           apiResponse.setCount(0);
           apiResponse.setData(Collections.emptyMap());

       }

       return ResponseEntity.ok(apiResponse);

   }

    @PostMapping(value = "/signUp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> memberSignUp(
            @RequestBody MemberParam inputMember,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

//            MemberDto signUpMember = memberService.memberSignUp(inputMember);

//            ObjectMapper mapper = new ObjectMapper();
//            String jsonString = mapper.writeValueAsString(signUpMember);
//            JSONObject jsonObject = new JSONObject(jsonString);
//
//            if(httpSession.getAttribute("loginMember") == null) {
//                httpSession.setAttribute("loginMember", signUpMember);
//            } else {
//
//            }

            apiResponse = new ApiResponse();

//            if(loginMember != null) {

//                apiResponse.setMessage(HttpStatus.OK.name());
//                apiResponse.setCode(HttpStatus.OK.value());
//                apiResponse.setCount(1);
//                apiResponse.setData(jsonObject.toString());
//
//            } else {

                apiResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
                apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                apiResponse.setCount(0);
                apiResponse.setData(Collections.emptyMap());

//            }

        } catch(Exception e) {

            e.printStackTrace();

            apiResponse = new ApiResponse();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> memberLogout(HttpServletRequest request) {

        ApiResponse apiResponse;

        try {

            HttpSession session = request.getSession();

            session.invalidate();

            apiResponse = new ApiResponse();

            apiResponse.setMessage(HttpStatus.OK.name());
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        } catch(Exception e) {

            e.printStackTrace();

            apiResponse = new ApiResponse();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

}
