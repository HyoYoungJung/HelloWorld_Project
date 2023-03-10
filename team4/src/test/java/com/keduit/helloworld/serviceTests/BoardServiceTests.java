package com.keduit.helloworld.serviceTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.dto.BoardDTO;
import com.keduit.helloworld.dto.PageRequestDTO;
import com.keduit.helloworld.dto.PageResultDTO;
import com.keduit.helloworld.service.BoardService;

@SpringBootTest
public class BoardServiceTests {

	@Autowired
	private BoardService boardService;
	
	@Test
	public void listTest() {
		
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
			
			PageResultDTO<BoardDTO, Object[]> result =
					boardService.getBoard2List(pageRequestDTO);
			
			for(BoardDTO boardDTO : result.getDtoList()) {
				System.out.println(boardDTO);
			}
		
	}
}