package com.keyboardsba.kakao;

import com.keyboardsba.common.EncryptUtils;
import com.keyboardsba.kakao.bo.KakaoBO;
import com.keyboardsba.kakao.entity.KakaoTokenResponse;
import com.keyboardsba.kakao.entity.KakaoUser;
import com.keyboardsba.kakao.entity.User;
import com.keyboardsba.kakao.repository.KakaoRepository;
import com.keyboardsba.user.bo.UserBO;
import com.keyboardsba.user.entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
    private KakaoBO kakaoBO;
    
    @Autowired
    private UserBO userBO;

    @GetMapping("/login")
    public RedirectView kakaologin() {
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + client_id + "&redirect_uri=" + redirect_uri;
        return new RedirectView(location);
    }

    @GetMapping("/callback")
    public String kakaoCallback(@RequestParam("code") String code, Model model,
    		HttpServletRequest request) {
        KakaoTokenResponse tokenResponse = kakaoBO.getAccessToken(code);
        String accesstoken = tokenResponse.getAccess_token();
        KakaoUser kakaoUser = kakaoBO.getKakaoUser(accesstoken);
        
        // 이메일과 닉네임 가져오기
        String email = kakaoUser.getKakao_account().getEmail();
        String nickname = kakaoUser.getKakao_account().getProfile().getNickname();
        String hashedpassword = EncryptUtils.SHA256(nickname+email);
        
        // TODO 만약 그 이메일과 닉네임, 코드 값이 넘어왔을때 일치하면 바로 로그인이 될수있게 처리.
        if(userBO.userIsExist(email, nickname) == null) {
        	User user = new User();
            user.setEmail(email);
            user.setNickname(nickname);
            model.addAttribute("email", email);
            model.addAttribute("nickname", nickname);
            model.addAttribute("accesstoken", hashedpassword);
            return "kakao/callback";
        } else {
        	UserEntity user = userBO.userIsExist(email, nickname);
        	HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("loginId", user.getLoginId()); // 나중에 글쓰기 할때 사람의 값을 가져오기 위해서
			session.setAttribute("userName", user.getName());
			return "redirect:/item/item-list-view";
        }
    }
}
