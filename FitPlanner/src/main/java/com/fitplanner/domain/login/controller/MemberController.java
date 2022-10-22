package com.fitplanner.domain.login.controller;

import com.fitplanner.domain.login.model.MemberModel;
import com.fitplanner.domain.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

   @PostMapping("/memberLogin")
   public ResponseEntity<ApiResponse> memberLogin(
           @RequestBody MemberModel memberModel) {

       ApiResponse apiResponse;

       try {

           apiResponse = new ApiResponse();

           apiResponse.setMessage("OK");
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
