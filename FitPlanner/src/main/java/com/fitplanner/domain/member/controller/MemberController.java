package com.fitplanner.domain.member.controller;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitplanner.core.mail.EmailSendComponent;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final EmailSendComponent emailSendComponent;

    private final MemberService memberService;

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

           Member findMember = Member.builder().id(memberParam.getId()).password(memberParam.getPassword()).delYn("N").build();
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
               apiResponse.setMessage(HttpStatus.CONFLICT.name() + " :: 사용중인 아이디");
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

            //현재 년도 구하기
            Calendar now = Calendar.getInstance(); //년월일시분초
            Integer currentYear = now.get(Calendar.YEAR);

            //태어난년도를 위한 세팅
            Integer birthYear = Integer.parseInt(signUpMember.getBirthday().substring(0,4));

            // 현재 년도 - 태어난 년도 => 나이 (만나이X)
            int age = (currentYear-birthYear);
            signUpMember.setAge(String.valueOf(age));
            signUpMember.setRegistId(signUpMember.getId());

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

                    JSONObject jsonObject = new JSONObject(jsonString);

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
            @AuthenticationPrincipal UserDetailsModel userDetailsModel,
            @RequestBody Member updateMember,
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession();

        ApiResponse apiResponse;

        try {

            //현재 년도 구하기
            Calendar now = Calendar.getInstance(); //년월일시분초
            Integer currentYear = now.get(Calendar.YEAR);

            //태어난년도를 위한 세팅
            Integer birthYear = Integer.parseInt(updateMember.getBirthday().substring(0,4));

            // 현재 년도 - 태어난 년도 => 나이 (만나이X)
            int age = (currentYear-birthYear);
            updateMember.setAge(String.valueOf(age));
            updateMember.setSeq(userDetailsModel.getUserSeq());

            if(updateMember.getPassword() != null && updateMember.getPassword() != "") {
                String encodePassword = passwordEncoder.encode(updateMember.getPassword());
                updateMember.setPassword(encodePassword);
            } else {
                updateMember.setPassword(userDetailsModel.getPassword());
            }

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

    @PostMapping(value = "/pwdReset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> memberPwdReset(
            @RequestBody Member member,
            HttpServletRequest request
    ) {

        long authNo = System.currentTimeMillis();

        ApiResponse apiResponse = new ApiResponse();

        try {

            HttpSession session = request.getSession();

            Optional<Member> checkMember = memberService.checkEmail(member.getEmail());

            if(checkMember.isPresent()) {

                Map<String, Object> tplData = new HashMap<>();
                tplData.put("authNo", authNo);

                SendTemplatedEmailResult result = emailSendComponent.send(member.getEmail(), "pwdReset", tplData);

                if (result.getMessageId() != null && result.getMessageId() != "") {

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("isNext", "Y");

                    apiResponse.setMessage(HttpStatus.OK.name());
                    apiResponse.setCode(HttpStatus.OK.value());
                    apiResponse.setCount(0);
                    apiResponse.setData(jsonObject.toString());

                    session.setAttribute("matchResetAuthNo", authNo);

                }

            } else {

                apiResponse.setMessage(HttpStatus.NOT_FOUND.name());
                apiResponse.setCode(HttpStatus.NOT_FOUND.value());
                apiResponse.setCount(0);
                apiResponse.setData(Collections.emptyMap());

            }

        } catch(Exception e) {

            e.printStackTrace();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping(value = "/pwdResetAuth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> memberPwdResetAuth(
            @RequestBody Map<String, Object> requestParam,
            HttpServletRequest request
    ) {

        JSONObject jsonObject = new JSONObject();

        ApiResponse apiResponse = new ApiResponse();

        try {

            HttpSession session = request.getSession();

            String authNo = String.valueOf( session.getAttribute("matchResetAuthNo") );
            String inputAuthNo = (String) requestParam.get("inputAuthNo");

            log.info("authNo = {}, inputAuthNo = {}", authNo, inputAuthNo);

            if(authNo.trim().equals( inputAuthNo.trim() )) {

                jsonObject.put("isNext", "Y");

                apiResponse.setMessage(HttpStatus.OK.name());
                apiResponse.setCode(HttpStatus.OK.value());
                apiResponse.setCount(0);
                apiResponse.setData(jsonObject.toString());

            } else {

                apiResponse.setMessage(HttpStatus.NOT_FOUND.name());
                apiResponse.setCode(HttpStatus.NOT_FOUND.value());
                apiResponse.setCount(0);
                apiResponse.setData(jsonObject.toString());

            }

        } catch(Exception e) {

            e.printStackTrace();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping(value = "/pwdResetExecute", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> memberPwdResetExecute(
            @RequestBody Map<String, Object> requestParam,
            HttpServletRequest request
    ) {

        JSONObject jsonObject = new JSONObject();

        ApiResponse apiResponse = new ApiResponse();

        try {

            String id           = String.valueOf( requestParam.get("id") );
            String newPassword  = String.valueOf( requestParam.get("newPassword") );

            byte[] decodeBytes = Base64.getDecoder().decode(newPassword.getBytes());
            String inputNewPassword = new String(decodeBytes);
            inputNewPassword = passwordEncoder.encode(inputNewPassword);

            Member updateMember = Member.builder().id(id).password(inputNewPassword).build();

            int resetComplete = memberService.resetPassword(updateMember);

            if(resetComplete > 0) {

                jsonObject.put("isNext", "Y");

                apiResponse.setMessage(HttpStatus.OK.name());
                apiResponse.setCode(HttpStatus.OK.value());
                apiResponse.setCount(0);
                apiResponse.setData(jsonObject.toString());

            } else {

                jsonObject.put("isNext", "N");

                apiResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
                apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                apiResponse.setCount(0);
                apiResponse.setData(jsonObject.toString());

            }


        } catch(Exception e) {

            e.printStackTrace();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping(value = "/signOut", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> memberSignOut(
            @AuthenticationPrincipal UserDetailsModel userDetailsModel,
            HttpServletRequest request
    ) {

        JSONObject jsonObject = new JSONObject();

        ApiResponse apiResponse = new ApiResponse();

        try {

            Member deleteMember = Member.builder().id(userDetailsModel.getUsername()).build();

            int deleteComplete = memberService.deleteMember(deleteMember);

            if(deleteComplete > 0) {

                jsonObject.put("isNext", "Y");

                apiResponse.setMessage(HttpStatus.OK.name());
                apiResponse.setCode(HttpStatus.OK.value());
                apiResponse.setCount(0);
                apiResponse.setData(jsonObject.toString());

            } else {

                jsonObject.put("isNext", "N");

                apiResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
                apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                apiResponse.setCount(0);
                apiResponse.setData(jsonObject.toString());

            }


        } catch(Exception e) {

            e.printStackTrace();

            apiResponse.setMessage(e.getLocalizedMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

}
