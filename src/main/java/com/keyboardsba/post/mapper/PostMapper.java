package com.keyboardsba.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.keyboardsba.post.domain.Post;

@Mapper
public interface PostMapper {
	
	public List<Map<String, Object>> selectPostListTest();
	
	public List<Post> selectPostList(
		@Param("standardId") Integer standardId, 
		@Param("direction") String direction, 
		@Param("limit") int limit);
	
	// 여기서 리턴 타입은 postId(int)
	public int selectPostIdAsSort(String sort);
	
	public void insertPost(
			@Param("userId") int userId,
			@Param("subject") String subject, 
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public Post selectPostByPostId( 
			@Param("postId") int postId);
	
	public void updatePostByPostId(
			@Param("postId") int postId,
			@Param("subject") String subject, 
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public int deletePostByPostId(int postId);
	
	public List<Post> selectAllPostList();
}