package com.fitplanner.domain.admin.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitplanner.core.mail.EmailSendComponent;
import com.fitplanner.core.response.ApiResponse;
import com.fitplanner.core.security.JwtTokenUtil;
import com.fitplanner.core.security.UserDetailsModel;
import com.fitplanner.domain.admin.member.model.AdminMember;
import com.fitplanner.domain.admin.member.service.AdminMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin/member/")
@RequiredArgsConstructor
public class AdminMemberController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final EmailSendComponent emailSendComponent;

    private final AdminMemberService adminMemberService;

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getMemberList(
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            List<AdminMember> getMemberList = adminMemberService.memberList();

            apiResponse = new ApiResponse();

            if(getMemberList != null) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("memberList", getMemberList.toArray());

                apiResponse.setMessage(HttpStatus.OK.name());
                apiResponse.setCode(HttpStatus.OK.value());
                apiResponse.setCount(getMemberList.size());
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

    /**
     * 관리자 정보수정 조회
     * */
    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAdmin(
            @RequestBody AdminMember adminMember,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

            if(adminMember.getSeq() != null && !adminMember.getSeq().equals("")) {

                int convertMemberSeq = adminMember.getSeq();
                Optional<AdminMember> findMember = adminMemberService.findMember(convertMemberSeq);
                AdminMember getAdminMember;

                if(findMember.isPresent()) {

                    getAdminMember = findMember.get();

                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(getAdminMember);

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
            @RequestBody AdminMember updateMember,
            HttpServletRequest request
    ) {

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

            if(updateMember.getPassword() != null && updateMember.getPassword() != "") {
                String encodePassword = passwordEncoder.encode(updateMember.getPassword());
                updateMember.setPassword(encodePassword);
            } else {
                updateMember.setPassword(userDetailsModel.getPassword());
            }

            int isUpdate = adminMemberService.updateMember(updateMember);

            apiResponse = new ApiResponse();

            apiResponse.setCount(isUpdate);

            if(isUpdate > 0) {

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
     * 회원 삭제
     * */
    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> deleteMember(
            @RequestBody AdminMember deleteMember,
            HttpServletRequest request
    ) {

        ApiResponse apiResponse;

        try {

            apiResponse = new ApiResponse();

            int isDelete = adminMemberService.deleteMember(deleteMember);

            apiResponse.setCount(isDelete);

            if(isDelete > 0) {

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

}
