package com.keyboardsba.kakao.entity;

import lombok.Data;

@Data
public class KakaoUser {
    private Long id;
    private String connected_at;
    private KakaoAccount kakao_account;

    // Getters and Setters
    @Data
    public static class KakaoAccount {
        private String email;
        private Profile profile;

        // Getters and Setters
    }

    @Data
    public static class Profile {
        private String nickname;

        // Getters and Setters
    }
}
