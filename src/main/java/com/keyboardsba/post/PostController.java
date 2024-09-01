package com.keyboardsba.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keyboardsba.post.bo.PostBO;
import com.keyboardsba.post.domain.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/post-list-view")
	public String PostListView(
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,
			HttpSession session, Model model) {
		// 로그인 여부 확인
		// int로 하는 순간 error가 날것이다. 왜냐면 int는 null값을 못받기 때문에.
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			// 비로그인일때 로그인 페이지로 이동
			return "redirect:/user/sign-in-view";
		}
		
		// DB 조회 (글목록에 대해서)
		List<Post> postList = postBO.getPostListByUserId(userId, prevIdParam, nextIdParam);
		int prevId = 0;
		int nextId = 0; // 비교를 할 때는 isempty, issize() < 0
		if(postList.isEmpty() == false) { // 글 목록이 비어있지 않을 때 페이징 정보 세팅
			prevId = postList.get(0).getId(); // 첫번째 칸 ID번
			nextId = postList.get(postList.size() - 1).getId(); // 마지막칸 번
			
			// 이전 방향의 끝인가? 그러면 0으로 세팅
			// prevId와 테이블의 제일 큰 숫자와 같으면 이전의 끝페이지
			if(postBO.isPrevLastPageByUserId(userId, prevId)) {
				prevId = 0;
			}
			
			// 다음 방향의 끝인가? 그러면 0으로 세팅
			// nextId와 테이블의 제일 작은 숫자가 같으면 다음의 끝 페이지라고 볼 수 있다.
			if(postBO.isNextLastPageByUserId(userId, nextId)) {
				nextId = 0;
			}
		}
		
		// 모델에 담기 (view가 있는곳)
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		model.addAttribute("postList", postList);
		
		// userId가 있음으로 그냥 리턴을 하게 되는 것이다.
		return "post/postList";
	}
	
	/**
	 * 글쓰기 화면
	 * @return
	 */
	
	@GetMapping("/post-create-view")
	public String postCreateView() {
		return "post/postCreate";
	}
	
	
	/**
	 * 글 상세 화면
	 */
	
	@GetMapping("/post-detail-view")
	public String postDetailView(
			@RequestParam("postId") int postId,
			Model model, HttpSession session
			) {
		// 쿼리문부터 생각해라. 어떤걸 가져오는지에 대해서 생각을 해야함. 글 번호와 아이디 번호까지 가져오는것
		// db 조회 = userId, postId
		int userId = (int)session.getAttribute("userId"); // 한번더 검사를 하는 과정, 만약 session으로 에러가 났다면 로그인이 풀렸다는 것이다.
		Post post = postBO.getPostByPostIdUserId(userId, postId);
		
		// 화면이 어디로 갈건지 model에 담기
		model.addAttribute("post", post);
		
		// 화면 이동
		return "post/postDetail";
	}
}