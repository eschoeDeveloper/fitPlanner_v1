package com.fitplanner.domain.admin.member.controller;

import com.amazonaws.services.simpleemail.model.SendTemplatedEmailResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitplanner.core.mail.EmailSendComponent;
import com.fitplanner.core.response.ApiResponse;
import com.fitplanner.core.security.JwtTokenUtil;
import com.fitplanner.core.security.UserDetailsModel;
import com.fitplanner.domain.admin.member.model.Admin;
import com.fitplanner.domain.admin.member.service.AdminService;

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
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final EmailSendComponent emailSendComponent;

    private final AdminService adminService;

    /**
     * 로그인
     * */
   @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ApiResponse> memberLogin(
           @RequestBody Map<String, Object> requestParam,
           HttpServletRequest request
   ) {

       HttpSession httpSession = request.getSession();

       ApiResponse apiResponse;

       try {

           String adminId = String.valueOf( requestParam.get("inputId") );
           String adminPassword = String.valueOf( requestParam.get("inputPassword") );

           Admin findAdmin = Admin.builder().id(adminId).password(adminPassword).roleSeq(1).delYn("N").build();
           int memberSeq = adminService.loginAdmin(findAdmin);

           apiResponse = new ApiResponse();

           if(memberSeq > 0) {

               Authentication authentication = authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(adminId, adminPassword));

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

           Optional<Admin> checkAdmin = adminService.findAdmin(decodeCheckId);

           apiResponse = new ApiResponse();

           if(checkAdmin.isPresent()) {

               apiResponse.setMessage(HttpStatus.CONFLICT.name() + " :: 사용중인 아이디");
               apiResponse.setCode(HttpStatus.CONFLICT.value());
               apiResponse.setData("N");
               apiResponse.setCount(1);

           } else {

               apiResponse.setMessage(HttpStatus.OK.name());
               apiResponse.setCode(HttpStatus.OK.value());
               apiResponse.setData("Y");
               apiResponse.setCount(0);

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
     * 관리자 추가
     * */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createAdmin(
            @RequestBody Admin createAdmin,
            HttpServletRequest request
    ) {

        HttpSession httpSession = request.getSession();

        ApiResponse apiResponse;

        try {

            //현재 년도 구하기
            Calendar now = Calendar.getInstance(); //년월일시분초
            Integer currentYear = now.get(Calendar.YEAR);

            //태어난년도를 위한 세팅
            Integer birthYear = Integer.parseInt(createAdmin.getBirthday().substring(0,4));

            // 현재 년도 - 태어난 년도 => 나이 (만나이X)
            int age = (currentYear-birthYear);
            createAdmin.setAge(String.valueOf(age));
            createAdmin.setRegistId(createAdmin.getId());

            int complete = adminService.createAdmin(createAdmin);

            log.info("Sign Up Member = {}", complete);

            apiResponse = new ApiResponse();
            apiResponse.setCount(complete);

            if(complete > 0) {

                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(createAdmin);
                JSONObject jsonObject = new JSONObject(jsonString);

                httpSession.setAttribute("memberSeq", createAdmin.getSeq());

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
     * 관리자 정보수정 조회
     * */
    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAdmin(
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession();

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

//            log.info("memberSeq = {}", session.getAttribute("memberSeq"));

            if(session.getAttribute("adminSeq") != null && !session.getAttribute("adminSeq").equals("")) {

                String adminSeq = String.valueOf(session.getAttribute("adminSeq"));
                int convertMemberSeq = Integer.parseInt(adminSeq);
                Optional<Admin> findAdmin = adminService.findAdmin(convertMemberSeq);
                Admin getAdmin;

                if(findAdmin.isPresent()) {

                    getAdmin = findAdmin.get();

                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(getAdmin);

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
            @RequestBody Admin updateAdmin,
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession();

        ApiResponse apiResponse;

        try {

            //현재 년도 구하기
            Calendar now = Calendar.getInstance(); //년월일시분초
            Integer currentYear = now.get(Calendar.YEAR);

            //태어난년도를 위한 세팅
            Integer birthYear = Integer.parseInt(updateAdmin.getBirthday().substring(0,4));

            // 현재 년도 - 태어난 년도 => 나이 (만나이X)
            int age = (currentYear-birthYear);
            updateAdmin.setAge(String.valueOf(age));
            updateAdmin.setSeq(userDetailsModel.getUserSeq());

            if(updateAdmin.getPassword() != null && updateAdmin.getPassword() != "") {
                String encodePassword = passwordEncoder.encode(updateAdmin.getPassword());
                updateAdmin.setPassword(encodePassword);
            } else {
                updateAdmin.setPassword(userDetailsModel.getPassword());
            }

            int isUpdate = adminService.updateAdmin(updateAdmin);

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
    public ResponseEntity<ApiResponse> adminLogout(HttpServletRequest request) {

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
