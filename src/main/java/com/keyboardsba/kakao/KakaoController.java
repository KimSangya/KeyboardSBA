package com.keyboardsba.kakao;

import com.keyboardsba.kakao.bo.KakaoService;
import com.keyboardsba.kakao.entity.KakaoTokenResponse;
import com.keyboardsba.kakao.entity.KakaoUser;
import com.keyboardsba.kakao.entity.User;
import com.keyboardsba.kakao.repository.KakaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/kakao")
@Controller
public class KakaoController {

    private static final String client_id = "970c46f4778e7f619bd5e2c8dd6af318";
    private static final String redirect_uri = "http://localhost/kakao/callback";

    @Autowired
    private KakaoService kakaoService;

    @GetMapping("/login")
    public RedirectView kakaologin() {
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + client_id + "&redirect_uri=" + redirect_uri;
        return new RedirectView(location);
    }

    @GetMapping("/callback")
    public String kakaoCallback(@RequestParam("code") String code, Model model) {
        KakaoTokenResponse tokenResponse = kakaoService.getAccessToken(code);
        KakaoUser kakaoUser = kakaoService.getKakaoUser(tokenResponse.getAccess_token());

        // 이메일과 닉네임 가져오기
        String email = kakaoUser.getKakao_account().getEmail();
        String nickname = kakaoUser.getKakao_account().getProfile().getNickname();

        // 사용자 정보 저장
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        
        model.addAttribute("email", email);
        model.addAttribute("nickname", nickname);

        return "kakao/callback";
    }

}
