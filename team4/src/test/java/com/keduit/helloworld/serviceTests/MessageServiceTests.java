package com.keduit.helloworld.serviceTests;

import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.dto.MessageDTO;
import com.keduit.helloworld.service.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@RequiredArgsConstructor
@Log4j2
public class MessageServiceTests {

	private final MessageService messageService;
	
	@Test
	/** 쪽지 리스트 조회(read, 받은사람 기준, 권한 0 or 1만 출력) */
	public void getListTest() {
		List<MessageDTO> list = messageService.getListAsGetter(10L);
		
		for(MessageDTO msgDto : list) {
			log.info(msgDto);
		}
	}
	
}