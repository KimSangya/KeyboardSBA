package com.keyboardsba.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keyboardsba.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findByloginId(String loginId);
	
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
	
	public UserEntity findById(int userId);
	
	public UserEntity findByEmailAndName(String email, String nickname);
	
	@Query("SELECT u FROM UserEntity u")
    public List<UserEntity> findAllUsers();
	
    public void deleteById(int userId);
}
