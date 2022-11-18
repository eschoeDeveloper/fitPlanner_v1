package com.fitplanner.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitplanner.core.security.JwtTokenUtil;
import com.fitplanner.core.security.UserDetailsModel;
import com.fitplanner.domain.member.model.CheckIdMapping;
import com.fitplanner.domain.member.model.Member;
import com.fitplanner.domain.member.model.MemberParam;
import com.fitplanner.domain.member.service.MemberService;
import com.fitplanner.core.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final MemberService memberService;

    private final JwtTokenUtil jwtTokenUtil;

    /**
     * 로그인
     * */
   @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ApiResponse> memberLogin(
           @RequestBody MemberParam memberParam,
           HttpServletRequest request
   ) {

       HttpSession httpSession = request.getSession();

       ApiResponse apiResponse;

       try {

           Member findMember = Member.builder().id(memberParam.getId()).password(memberParam.getPassword()).build();
           int memberSeq = memberService.loginMember(findMember);

           apiResponse = new ApiResponse();

           if(memberSeq > 0) {

               Authentication authentication = authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(memberParam.getId(), memberParam.getPassword()));

               SecurityContextHolder.getContext().setAuthentication(authentication);
               String jwtToken = jwtTokenUtil.generateJwtToken(authentication);

               UserDetailsModel userDetails = (UserDetailsModel) authentication.getPrincipal();

               log.info("userDetails => {}", userDetails.getUsername());

               List<String> roles = userDetails.getAuthorities().stream()
                       .map(item -> item.getAuthority())
                       .collect(Collectors.toList());

               JSONObject jsonObject = new JSONObject();

               jsonObject.put("ssoLogin", "Y");
               jsonObject.put("jwtToken", jwtToken);

               httpSession.setAttribute("memberSeq", memberSeq);

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

        HttpSession httpSession = request.getSession();

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

                httpSession.setAttribute("memberSeq", signUpMember.getSeq());
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
     * 회원정보수정 조회
     * */
    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getMember(
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession();

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

            log.info("memberSeq = {}", session.getAttribute("memberSeq"));

            if(session.getAttribute("memberSeq") != null && !session.getAttribute("memberSeq").equals("")) {

                String memberSeq = String.valueOf(session.getAttribute("memberSeq"));
                int convertMemberSeq = Integer.parseInt(memberSeq);
                Optional<Member> findMember = memberService.findById(convertMemberSeq);
                Member getMember;

                if(findMember.isPresent()) {

                    getMember = findMember.get();

                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(getMember);

                    log.info("findMember = {}", jsonString);

                    JSONObject jsonObject = new JSONObject(jsonString);

                    log.info("findMember = {}", jsonObject.toString());

                    apiResponse.setMessage(HttpStatus.OK.name());
                    apiResponse.setCode(HttpStatus.OK.value());
                    apiResponse.setData(jsonObject.toString());
                    apiResponse.setCount(1);

                } else {

                    apiResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
                    apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    apiResponse.setData(new JSONObject());
                    apiResponse.setCount(0);

                }

            } else {

                apiResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
                apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                apiResponse.setData(new JSONObject());
                apiResponse.setCount(0);

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
     * 회원정보수정
     * */
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateMember(
            @RequestBody Member updateMember,
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession();

        ApiResponse apiResponse;

        try {

            int isUpdate = memberService.updateMember(updateMember);

            apiResponse = new ApiResponse();

            apiResponse.setCount(isUpdate);

            if(isUpdate > 0) {

                session.invalidate();

                apiResponse.setMessage(HttpStatus.OK.name());
                apiResponse.setCode(HttpStatus.OK.value());
                apiResponse.setData("Y");

            } else {

                apiResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
                apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                apiResponse.setData("N");

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
