package com.fitplanner.domain.fitHealthGym.controller;

import com.amazonaws.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitplanner.core.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * package     : com.fitplanner.domain.fitHealthGym.controller
 * file        : FitHealthGymController
 * author      : choeuiseung
 * date        : 2022/10/31
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/31                choeuiseung                 최초 생성
 * =======================================================
 */
@Slf4j
@RestController
@RequestMapping("/api/fitHealthGym")
@RequiredArgsConstructor
public class FitHealthGymController {

    private final Environment env;

    @PostMapping("/list")
    public ResponseEntity<ApiResponse> getHealthGymList() {

        ApiResponse apiResponse;

        try {

//            log.info("naver.api.domain = {}", env.getProperty("naver.api.domain"));
//            log.info("naver.api.url = {}", env.getProperty("naver.api.url"));
//            log.info("naver.api.clientId = {}", env.getProperty("naver.api.clientId"));
//            log.info("naver.api.clientSecret = {}", env.getProperty("naver.api.clientSecret"));

            String query = "헬스장";
            ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(query);
            String encodeQuery = StandardCharsets.UTF_8.decode(byteBuffer).toString();

            URI naverApiUri = UriComponentsBuilder
                    .fromUriString(env.getProperty("naver.api.domain"))
                    .path(env.getProperty("naver.api.url"))
                    .queryParam("query", encodeQuery)
                    .queryParam("display", 20)
                    .queryParam("start", 1)
                    .queryParam("sort", "random")
                    .encode()
                    .build()
                    .toUri();

            RestTemplate restTemplate = new RestTemplate();

            RequestEntity<Void> requestEntity = RequestEntity
                    .get(naverApiUri)
                    .header("X-Naver-Client-Id", env.getProperty("naver.api.clientId"))
                    .header("X-Naver-Client-Secret", env.getProperty("naver.api.clientSecret"))
                    .build();

            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

            String result = responseEntity.getBody();
            JSONObject jsonObject = new JSONObject(result);

            apiResponse = new ApiResponse();

//            log.info("jsonObject = {}", jsonObject.getInt("total"));
//            log.info("jsonObject = {}", jsonObject.getJSONArray("items"));

            apiResponse.setCount(jsonObject.getInt("total"));
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setMessage("OK");
            apiResponse.setData(jsonObject.getJSONArray("items").toString());

        } catch (Exception e) {

            apiResponse = new ApiResponse();

            apiResponse.setMessage(e.getMessage());
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setCount(0);
            apiResponse.setData(Collections.emptyMap());

        }

        return ResponseEntity.ok(apiResponse);

    }

}
