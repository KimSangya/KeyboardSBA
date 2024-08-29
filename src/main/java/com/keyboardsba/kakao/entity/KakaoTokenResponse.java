package com.keyboardsba.kakao.entity;

import lombok.Data;

@Data
public class KakaoTokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;

    // Getters and Setters
}
