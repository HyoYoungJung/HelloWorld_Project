package com.keduit.helloworld.repositoryTests;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.entity.Member;
import com.keduit.helloworld.entity.Message;
import com.keduit.helloworld.repository.MemberRepository;
import com.keduit.helloworld.repository.MessageRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MessageRepositoryTests {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	/** 쪽지 등록 테스트(create) */
	public void insertMsgTest() {
		
		IntStream.rangeClosed(1, 2000).forEach(i -> {

			long num = (long) Math.random()*50;
			if(num != 25) {
				long a = (long) (num) + 1;
				long b = (long) (50 - num) + 1;
				Long c = (long) (Math.random() * 3000) + 1;

				Message entity = Message.builder()
						.memberGet(a)
						.memberGive(b)
						.title("제목" + i)
						.content("내용" + i)
						.view(0L) //최초 전송 시 권한: 0
						.boardCommentNum(c)
						.build();
				messageRepository.save(entity);
			}
		});
	}
	
	@Test
	/** 받은 쪽지 리스트 조회(read, 받은사람 기준, 권한 0 or 1만 출력) */
	public void getMsgListAsGetter() {
		List<Message> list = messageRepository.getMsgInfoAsGetter(10L);
		
		for (Message msg : list) {
		System.out.println("쪽지 번호 : " + msg.getMessageNum() + ", " +
						   "받은 사람 : " + msg.getMemberGet() + ", " +
						   "보낸 사람 : " + msg.getMemberGive() + ", " + 
						   "등록 일자 : " + msg.getRegDate());
		}
	}
	
	@Test
	/** 보낸 쪽지 리스트 조회(read, 보낸사람 기준, 권한 0 or 2만 출력) */
	public void selectMsgListAsGiver() {
		List<Message> list = messageRepository.getMsgInfoAsGiver(1L);
		
		for (Message msg : list) {
		System.out.println("쪽지 번호 : " + msg.getMessageNum() + ", " +
						   "보낸 사람 : " + msg.getMemberGive() + ", " + 
						   "받는 사람 : " + msg.getMemberGet() + ", " +
						   "등록 일자 : " + msg.getRegDate());
		}
	}
	
	@Test
	/** 쪽지 상세 조회(read) */
	public void selectMsgTest() {
		Optional<Message> result = messageRepository.findById(1L);
		System.out.println(result);
	}
	
	@Test
	/** 받은 사람이 쪽지 삭제 시 보기권한 변경(update: 보기권한 +2, delete: 권한 3일때) */
	public void updateViewAsGetter() {
		
		long messageNum = 1L;
		
		Optional<Message> result = messageRepository.findById(messageNum);
		
		Message message = result.orElseThrow();
		
		message.changes(message.getView() + 2); //보기권한 0은 2, 1은 3으로 변경
		
		if(message.getView() == 3) {
			messageRepository.deleteById(messageNum);
		}
				
		System.out.println(messageRepository.save(result.get()));
	}
	
	@Test
	/** 보낸 사람이 쪽지 삭제 시 보기권한 변경(update: 보기권한 +1, delete: 권한 3일때) */
	public void updateViewAsGiver() {
		
		long messageNum = 1L;

		Optional<Message> result = messageRepository.findById(messageNum);
		
		Message message = result.orElseThrow();
		
		message.changes(message.getView() + 1); //보기권한 0은 1, 2는 3으로 변경
		
		if(message.getView() == 3) {
			messageRepository.deleteById(messageNum);
		}
		
		System.out.println(messageRepository.save(result.get()));
	}

}
	
