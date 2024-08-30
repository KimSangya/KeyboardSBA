package com.keyboardsba.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyboardsba.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findByloginId(String loginId);
	
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
	
	public UserEntity findById(int userId);
	
	public UserEntity findByEmailAndName(String email, String nickname);
}
