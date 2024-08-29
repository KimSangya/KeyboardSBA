package com.keyboardsba.kakao.repository;

import com.keyboardsba.kakao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoRepository extends JpaRepository<User, Long> {
	
}
