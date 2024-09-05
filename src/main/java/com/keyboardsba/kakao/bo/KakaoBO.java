package com.keyboardsba.kakao.bo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.keyboardsba.kakao.entity.KakaoTokenResponse;
import com.keyboardsba.kakao.entity.KakaoUser;

@Service
public class KakaoBO {

    private static final String tokenUri = "https://kauth.kakao.com/oauth/token";
    private static final String userInfoUri = "https://kapi.kakao.com/v2/user/me";

    public KakaoTokenResponse getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", "970c46f4778e7f619bd5e2c8dd6af318");
        params.put("client_secret", "H4An6FIZaQmDl5YFaRx4ETCXXj4s9M5c");
        params.put("redirect_uri", "http://43.203.120.245:8080/kakao/callback");
        params.put("code", code);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(tokenUri)
            .queryParam("grant_type", params.get("grant_type"))
            .queryParam("client_id", params.get("client_id"))
            .queryParam("client_secret", params.get("client_secret"))
            .queryParam("redirect_uri", params.get("redirect_uri"))
            .queryParam("code", params.get("code"));

        return restTemplate.postForObject(uriBuilder.toUriString(), null, KakaoTokenResponse.class);
    }

    public KakaoUser getKakaoUser(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Access Token: " + accessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(userInfoUri);

        return restTemplate.exchange(
            uriBuilder.toUriString(),
            HttpMethod.GET,
            entity,
            KakaoUser.class
        ).getBody();
    }

}
