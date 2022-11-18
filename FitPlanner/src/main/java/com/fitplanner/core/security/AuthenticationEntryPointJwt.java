package com.fitplanner.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitplanner.core.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
public class AuthenticationEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.error("API 인증 에러 : {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(HttpServletResponse.SC_UNAUTHORIZED);
        apiResponse.setData(new JSONObject());
        apiResponse.setCount(0);
        apiResponse.setMessage("");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), apiResponse);

    }
}
