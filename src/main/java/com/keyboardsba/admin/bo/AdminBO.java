package com.keyboardsba.admin.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyboardsba.alert.bo.AlertBO;
import com.keyboardsba.alert.domain.Alert;
import com.keyboardsba.item.bo.ItemBO;
import com.keyboardsba.item.domain.Item;
import com.keyboardsba.post.bo.PostBO;
import com.keyboardsba.post.domain.Post;
import com.keyboardsba.user.bo.UserBO;
import com.keyboardsba.user.entity.UserEntity;

@Service
public class AdminBO {

	@Autowired
	private ItemBO itemBO;
	
	@Autowired
	private AlertBO alertBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
	public List<Item> getItemList() {
		return itemBO.getItemList();
	}
	
	public List<Alert> getAlertList() {
		return alertBO.getAlertList();
	}
	
	public List<UserEntity> getUserList() {
		return userBO.getUserList();
	}
	
	public List<Post> getPostList() {
		return postBO.getPostList();
	}
}
