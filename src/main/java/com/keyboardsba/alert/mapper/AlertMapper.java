package com.keyboardsba.alert.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.keyboardsba.alert.domain.Alert;

@Mapper
public interface AlertMapper {
	public void insertAlert(
			@Param("userId") int userId,
			@Param("subject") String subject, 
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public List<Alert> selectAlertList();
}
