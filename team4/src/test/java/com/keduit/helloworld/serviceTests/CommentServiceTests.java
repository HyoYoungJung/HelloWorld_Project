package com.keduit.helloworld.serviceTests;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.dto.CommentDTO;
import com.keduit.helloworld.entity.Member;
import com.keduit.helloworld.repository.MemberRepository;
import com.keduit.helloworld.service.CommentService;

@SpringBootTest
public class CommentServiceTests {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void testGetList() {
		
		Long boardNum = 305L;
		
		List<CommentDTO> commentDTOList = commentService.getList(boardNum);
		commentDTOList.forEach(commentDTO -> System.out.println(commentDTO));
	}
	
	
	@Test
	public void testtest() {
		
		Long boardNum = 305L;
		
		Member member = memberRepository.getBoardByBno(boardNum);
		
		System.out.println("값은? " + member);
	}

	@Test
	public void removerTest() {
		
		commentService.boardRemove(600L);
	}
	
	
}