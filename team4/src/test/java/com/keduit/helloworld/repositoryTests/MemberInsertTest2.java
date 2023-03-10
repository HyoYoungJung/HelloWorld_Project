package com.keduit.helloworld.repositoryTests;


import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.keduit.helloworld.entity.Member;
import com.keduit.helloworld.entity.MemberRole;
import com.keduit.helloworld.repository.MemberRepository;

@SpringBootTest
public class MemberInsertTest2 {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void insertTest() {
		IntStream.rangeClosed(1, 50).forEach(i->{
			Member member = Member.builder()
					.id("user"+i)
					.name("이름"+i)
					.pw(passwordEncoder.encode("1111"))
					.nickname("닉네임"+i)
					.email(i + "@abc.com")
					.purview(false)
					.exvalue(800L)
					.build();
				member.addMemberRole(MemberRole.USER);
				memberRepository.save(member);
		});
		
	}
	
	/** 관리자 아이디 만들기 */
	@Test
	public void insertAdmin() {
		Member member = Member.builder().id("admin")
				.name("관리자")
				.pw(passwordEncoder.encode("1111"))
				.nickname("관리자")
				.email("admin@abc.com")
				.purview(false)
				.build();
			member.addMemberRole(MemberRole.ADMIN);
			memberRepository.save(member);
	}
	
	@Test
	public void testRead() {
		Optional<Member> result = memberRepository.findByEmail("2aa", false);
		Member member = result.get();
		
		System.out.println("맴버 읽어올 수 있는지 : " + member);
		
		
	}
}