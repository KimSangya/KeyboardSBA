package com.keyboardsba.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyboardsba.item.bo.ItemBO;
import com.keyboardsba.user.entity.UserEntity;
import com.keyboardsba.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByloginId(loginId);
	}
	
	public UserEntity addUser(String loginId, 
			String password, String name, String email, String phoneNumber) {
		return userRepository.save(UserEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.phoneNumber(phoneNumber)
				.build());
	}
	
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
	
	public UserEntity selectUserId(int userId) {
		return userRepository.findById(userId);
	}
}
