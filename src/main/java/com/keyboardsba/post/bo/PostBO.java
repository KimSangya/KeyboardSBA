package com.keyboardsba.post.bo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.keyboardsba.common.FileManagerService;
import com.keyboardsba.item.domain.Item;
import com.keyboardsba.post.mapper.PostMapper;
import com.keyboardsba.post.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostBO {
	// private Logger log = LoggerFactory.getLogger(PostBO.class);
	// private Logger log = LoggerFactory.getLogger(this.getClass()); // 쓰고 싶은 친구에게 logger를 찍어서 보내는 역할
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManagerService;
	// 페이징 정보 필드(limit)
	private static final int POST_MAX_SIZE = 3;
	
	
	// input : 로그인 된 사람의 userId
	// output : List<Post>
	public List<Post> getPostList(Integer prevId, Integer nextId) {
		// 게시글 번호 10 9 8 | 7 6 5  | 4 3 2 | 1
		// 만약 내가 432 페이지에 있을 때,
		// 1) 다음 : 2보다 작은 3개 DESC
		// 2) 이전 : 4보다 큰 3개 ASC =>  567 => BO에서 reverse 7, 6, 5
		// 3) 페이징 정보가 없을 때, 최신순 3 DESC해서 확인 경우는 3가지가 있음.
		// if 문에서 따로 설정.
		Integer standardId = null; // 기준 postId
		String direction = null; // 방향
		if(prevId != null) { // 2) 이전
			standardId = prevId;
			direction = "prev";
			
			List<Post> postList = postMapper.selectPostList(standardId, direction, POST_MAX_SIZE);
			// [5,6,7] => [7,6,5]
			Collections.reverse(postList); // 뒤집고 저장
			
			return postList;
		} else if(nextId != null) { // 1) 다음
			standardId = nextId;
			direction = "next";
		}
		
		// 3) 페이징 정보 없음, 1) 다음
		return postMapper.selectPostList(standardId, direction, POST_MAX_SIZE);
	}
	
	// 이전 페이지의 마지막인가?
	public boolean isPrevLastPage(int prevId) {
		int maxPostId = postMapper.selectPostIdAsSort("DESC");
		return maxPostId == prevId; // 같으면 마지막이다, 아니면 아니다.
	}
	
	// 다음 페이지의 마지막인가?
	public boolean isNextLastPage(int nextId) {
		int minPostId = postMapper.selectPostIdAsSort("ASC");
		return minPostId == nextId;
	}
	
	
	// input : userId, postId
	// output : Post or null (단건이니까 null값이 리턴 될 수도 있다.)
	public Post getPostByPostId(int PostId) {
		return postMapper.selectPostByPostId(PostId) ;
	}
	
	// input : 파라미터들
	// output : X
	public void addPost(int userId, String userLoginId,
			String subject, String content, MultipartFile file) {
		// userLoginId는 따로 저장하려고 보내주는 값이라고 생각하면 됨.
		
		String imagePath = null;
		if(file != null) {
			// 업로드 할 이미지가 있을때에만 업로드를 해주는 것이다.
			// 이미지 업로드할 기능마다 족족 다 바꿔줘야하니, 코드가 중복되는 방식을 막으려면 class를 만들어서 보완하게 하면 된다.
			imagePath = fileManagerService.uploadFile(file, userLoginId);
		}
		
		postMapper.insertPost(userId, subject, content, imagePath);
	}
	
	// input : 파라미터들
	// output : X
	
	public void updatePostbyPostId(
			int userId, String loginId,
			int postId, String subject, String content,
			MultipartFile file) {
		
		// 기존 글 가져온다 (1.이미지 교체시 삭제하기 위해 2. 업데이트 대상이 있는지 확인)
		// log를 찍을 때, sysout을 사용하면 안된다. 한명당 그 서비스를 사용할때, 그 쓰레드를 하나씩 사용하기 때문에 홈페이지 자체가 느려질수도있다.
		Post post = postMapper.selectPostByPostId(postId);
		if (post == null) {
			// method 설명 : 로그 레벨 DEBUG, INFO, WARN, ERROR, FATAL => 왼쪽부터 사용빈도수가 높고 오른쪽으로 갈수록 사용 빈도수가 낮다. 
			log.warn("[글 수정] post is null. postId:{}", postId); // 와일드 카드 문법을 사용할때 ,를 사용한다면 따로 확인이 가능하다.
			return;
		}
		
		// 파일이 있으면
		// 1) 새 이미지 업로드 / 만약 실패했을때 바꾸지 않는다.
		// 2) 1번 단계가 성공하면 기존 이미지가 있을때 삭제
		String imagePath = null;
		
		// 파일이 있다면
		if(file != null) {
			// 새 이미지 업로드
			imagePath = fileManagerService.uploadFile(file, loginId);
			
			// 업로드 성공 시(imagePath == null이 아닐 때) 기존 이미지가 있다면 제거
			if(imagePath != null && post.getImageUrl() != null) {
				// 폴더와 이미지 제거(서버에서)
				fileManagerService.deleteFile(post.getImageUrl());
			}
		}
		
		// db update
		postMapper.updatePostByPostId(postId, subject, content, imagePath);
	}
	
	// input : postId, userId
	// output : X
	
	public void deletePostByPostIdUserId(int postId, int userId) {
		
		// 기존글 가져오는 부분
		Post post = postMapper.selectPostByPostId(postId);
		
		// 원래는 있어야하는데, 만약 없다면
		if (post == null) {
			log.info("[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
			return;
		}
		// post DB delete
		// 만약 리턴 행이 1인 경우, 그 친구를 자세하게 삭제를 가능하게 할수있다.
		int rowCount = postMapper.deletePostByPostId(postId);
		
		
		// TODO: 이미지가 존재하면 삭제한다. 삭제된 행도 1일때 삭제하게 만든다.
		// 업로드 성공 시(imagePath == null이 아닐 때) 기존 이미지가 있다면 제거
		if(rowCount > 0 && post.getImageUrl() != null) {
			// 폴더와 이미지 제거(서버에서)
			fileManagerService.deleteFile(post.getImageUrl());
		}	
	}
	
	public List<Post> getPostList() {
		List<Post> ItemList = postMapper.selectAllPostList();
		return ItemList;
	}
}