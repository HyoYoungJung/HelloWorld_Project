package com.keduit.helloworld.repositoryTests;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.entity.Board;
import com.keduit.helloworld.entity.Comment;
import com.keduit.helloworld.entity.Member;
import com.keduit.helloworld.repository.BoardRepository;
import com.keduit.helloworld.repository.CommentRepository;
import com.keduit.helloworld.repository.MemberRepository;

@SpringBootTest
public class BoardRepositoryTests {
	
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	/** 더미생성 */
	public void insertBoardTest() { // 더미
		
		IntStream.rangeClosed(1, 500).forEach(i ->{
			Board board = Board.builder()
							.title("[title] " + i+" 번")
							.content("내용 -- " + i)
							.tag("")
							.boardcase((long) (Math.random()*3)+1)
							.memberNum((long) (Math.random()*50)+1)
							.build();
			
			boardRepository.save(board);
		});
		
	}
	
	@Test
	/** 읽기 */
	public void selectBoardTest() {
		System.out.println("타냐");
		Optional<Board> result = boardRepository.findById(2L);
		
		System.out.println(result);
	}
	
	@Test
	/** 수정 */
	public void updateBoardTest() {
		Board board = Board.builder()
							.boardNum(2L)
							.title("수정된 -- 1")
							.content("수정된 내용 -- 1")
							.build();
		
		System.out.println(boardRepository.save(board));
	}
	
	@Test
	/** 삭제 */
	public void deleteBoard() {
		boardRepository.deleteById(2L);
	}
	
	@Test
	public void testReadOne() {
		Long result = commentRepository.getBoardByBno(47L);
		
		
		
		System.out.println(result);
		
	}
	@Test
	public void testReadOneM() {
		Member result = memberRepository.getBoardByBno(47L);
		
		
		
		System.out.println(result);
		
	}
	
	
	@Test
	public void test2() {
		String str =  "board_num";
		Integer num = 1 ; 

		List<Board> list = boardRepository.temp(str,num);
		
		System.out.println(list);
		
		
	}

}