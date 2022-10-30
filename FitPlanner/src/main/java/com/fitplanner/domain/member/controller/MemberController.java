package com.fitplanner.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitplanner.domain.member.model.CheckIdMapping;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인
     * */
   @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ApiResponse> memberLogin(
           @RequestBody MemberParam memberParam,
           HttpServletRequest request
   ) {

       ApiResponse apiResponse;

       try {

           Member findMember = Member.builder().id(memberParam.getId()).password(memberParam.getPassword()).build();
           int loginMember = memberService.loginMember(findMember);

           apiResponse = new ApiResponse();

           if(loginMember > 0) {

               HttpSession httpSession = request.getSession();

               JSONObject jsonObject = new JSONObject();
               if(httpSession.getAttribute("ssoLogin") == null) {
                   jsonObject.put("ssoLogin", "Y");
               }

               apiResponse.setMessage(HttpStatus.OK.name());
               apiResponse.setCode(HttpStatus.OK.value());
               apiResponse.setCount(1);
               apiResponse.setData(jsonObject.toString());

           } else {

               JSONObject jsonObject = new JSONObject();
               jsonObject.put("ssoLogin", "N");

               apiResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
               apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
               apiResponse.setCount(0);
               apiResponse.setData(jsonObject.toString());

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

   @PostMapping(value = "/checkId/{checkId}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ApiResponse> memberIdCheck(
     @PathVariable("checkId") String checkId
   ) {

       ApiResponse apiResponse;

       try {

           Base64.Decoder decoder = Base64.getDecoder();
           byte[] decodedBytes = decoder.decode(checkId.getBytes(Charset.defaultCharset()));
           String decodeCheckId = new String(decodedBytes, Charset.defaultCharset());

//           log.info("decodeCheckId = {}" , decodeCheckId);

           List<CheckIdMapping> checkMember = memberService.findById(decodeCheckId);

           apiResponse = new ApiResponse();

           apiResponse.setCount(checkMember.size());

           if(apiResponse.getCount() == 0) {
               apiResponse.setMessage(HttpStatus.OK.name());
               apiResponse.setCode(HttpStatus.OK.value());
               apiResponse.setData("Y");
           } else {
               apiResponse.setMessage("사용중인 아이디");
               apiResponse.setCode(HttpStatus.CONFLICT.value());
               apiResponse.setData("N");
           }

       } catch( Exception e ) {

           e.printStackTrace();

           apiResponse = new ApiResponse();

           apiResponse.setMessage(e.getLocalizedMessage());
           apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
           apiResponse.setCount(0);
           apiResponse.setData(Collections.emptyMap());

       }

       return ResponseEntity.ok(apiResponse);

   }

    /**
     * 회원가입
     * */
    @PostMapping(value = "/signUp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> memberSignUp(
            @RequestBody Member signUpMember,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            int complete = memberService.signUpMember(signUpMember);

            log.info("Sign Up Member = {}", complete);

            apiResponse = new ApiResponse();
            apiResponse.setCount(complete);

            if(complete > 0) {

                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(signUpMember);
                JSONObject jsonObject = new JSONObject(jsonString);

                HttpSession httpSession = request.getSession();

                httpSession.setAttribute("loginMember", signUpMember);
                jsonObject.put("ssoLogin", "Y");

                apiResponse.setMessage(HttpStatus.OK.name());
                apiResponse.setCode(HttpStatus.OK.value());
                apiResponse.setData(jsonObject.toString());

            } else {

                apiResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
                apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
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


    /**
     * 로그아웃
     * */
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
