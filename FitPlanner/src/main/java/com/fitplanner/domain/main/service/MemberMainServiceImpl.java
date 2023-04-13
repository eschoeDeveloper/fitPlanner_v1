package com.fitplanner.domain.main.service;

import com.fitplanner.domain.main.repository.MemberMainRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service("memberMainService")
@RequiredArgsConstructor
public class MemberMainServiceImpl implements MemberMainService {

    private final Environment env;
    private final MemberMainRepository memberMainRepository;

    @Override
    public List<Map<String, Object>> getFitScheduleList(int memberSeq) {
        return memberMainRepository.getFitScheduleList(memberSeq);
    }

    @Override
    public List<Map<String, Object>> getFoodDietList(int memberSeq) {
        return memberMainRepository.getFoodDietList(memberSeq);
    }

    @Override
    public JSONObject getFitHealthGymList(int memberSeq) {

        String query = "헬스장";
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(query);
        String encodeQuery = StandardCharsets.UTF_8.decode(byteBuffer).toString();

        URI naverApiUri = UriComponentsBuilder
                .fromUriString(env.getProperty("naver.api.domain"))
                .path(env.getProperty("naver.api.url"))
                .queryParam("query", encodeQuery)
                .queryParam("display", 5)
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

        return jsonObject;

    }

}
