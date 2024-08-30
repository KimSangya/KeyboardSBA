package com.keyboardsba.kakao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyboardsba.common.EncryptUtils;
import com.keyboardsba.kakao.bo.KakaoBO;
import com.keyboardsba.kakao.entity.KakaoTokenResponse;
import com.keyboardsba.kakao.entity.KakaoUser;
import com.keyboardsba.kakao.entity.User;
import com.keyboardsba.user.bo.UserBO;
import com.keyboardsba.user.entity.UserEntity;

@RequestMapping("/kakao")
@RestController
public class KakaoRestController {
	
    private static final String client_id = "970c46f4778e7f619bd5e2c8dd6af318";
    private static final String redirect_uri = "http://localhost/kakao/callback";
	
    @Autowired
    private KakaoBO kakaoBO;
    
    @Autowired
    private UserBO userBO;
	
	@PostMapping("/callback")
    public Map<String, Object> login(@RequestParam("code") String code, Model model) {
        KakaoTokenResponse tokenResponse = kakaoBO.getAccessToken(code);
        String accesstoken = tokenResponse.getAccess_token();
        KakaoUser kakaoUser = kakaoBO.getKakaoUser(accesstoken);
        
        // 이메일과 닉네임 가져오기
        String email = kakaoUser.getKakao_account().getEmail();
        String nickname = kakaoUser.getKakao_account().getProfile().getNickname();
        String hashedpassword = EncryptUtils.SHA256(nickname+email);
        
        // TODO 만약 그 이메일과 닉네임, 코드 값이 넘어왔을때 일치하면 바로 로그인이 될수있게 처리.
        Map<String, Object> user = userBO.userIsExist(email, nickname, hashedpassword);
        
		Map<String, Object> result = new HashMap<>();
		
        return result;
    }
}
