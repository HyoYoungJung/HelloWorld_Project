package com.keduit.helloworld.repositoryTests;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.entity.Board;
import com.keduit.helloworld.entity.Comment;
import com.keduit.helloworld.repository.BoardRepository;
import com.keduit.helloworld.repository.CommentRepository;
import com.keduit.helloworld.repository.MemberRepository;

@SpringBootTest
public class CommentRepositoryTests {
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private MemberRepository memberRepository;


	@Test
	/** 더미 Test */
	public void insertReply() {

		IntStream.rangeClosed(1, 3000).forEach(i -> {
			long boardNum = (long)(Math.random() * 500) + 1;
			long commenter = (long)(Math.random() * 50) + 1;

			Comment comment = Comment.builder()
					.boardNum(boardNum)
					.commenterNum(commenter)
					.commentContent("뎃글 " + i + " 번 내용입니다.")
					.build();
			commentRepository.save(comment);
		});
	}
	
	@Test
	public void readTest() {
		
		List<Comment> cc = commentRepository.getCommentlist(607L);
		
		for(Comment i : cc) {
			System.out.println(i);
		}
		
		
		for(Comment i : cc) {
			System.out.println(i.getRegDate());
			System.out.println(i.getUpdateDate());
		}
		
		
	}

	@Test
	public void insertcommentTest() {
		IntStream.rangeClosed(1, 1000).forEach(i -> {
			Comment comment = Comment.builder().boardNum((long)(Math.random()*200)+1)
												.commentContent(i + "번째 내용")
												.viewpicture(i+"번사진.jpg")
												.price((long)i * 1000)
												.clikes((long)i*50)
												.build();
			commentRepository.save(comment);
		});
	}
	
	@Test
	public void testtest() {
		
		Optional<Comment> commentcheck = commentRepository.findById(1L);
		
		System.out.println(commentcheck);
	}
	
}