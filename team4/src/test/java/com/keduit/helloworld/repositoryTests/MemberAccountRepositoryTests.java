package com.keduit.helloworld.repositoryTests;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.entity.Member;
import com.keduit.helloworld.entity.MemberAccount;
import com.keduit.helloworld.repository.MemberAccountRepository;
import com.keduit.helloworld.repository.MemberRepository;

@SpringBootTest
public class MemberAccountRepositoryTests {

	@Autowired
	private MemberAccountRepository memberAccountRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	/** 회원간 거래내역 등록 테스트(create) */
	public void insertAccount() {
		
		IntStream.rangeClosed(1, 500).forEach(i->{
			long num = (long) Math.random()*50;
			if(num != 25) {
				long a = (long) (num) + 1;
				long b = (long) (50 - num) + 1;

				MemberAccount entity = MemberAccount.builder()
						.memberBuyer(a)
						.memberSeller(b)
						.payment(i * 1000L)
						.build();
				memberAccountRepository.save(entity);
			}
		});
	}
	
	@Test
	/** 회원간 거래내역 조회 테스트(read, 구매자=질문자 기준) */
	public void selectPayListAsBuyer() {
		
		long buyerMemNum = 2;
		
		List<MemberAccount> result1 = memberAccountRepository.getPayListAsBuyer(buyerMemNum);
		List<Member> result2 = memberRepository.getMemNumAsBuyer(buyerMemNum);
		
		for(int i = 0; i <= result1.size()-1; i++) {
			System.out.println(result1.get(i).getRegDate() + ", " + 
								result1.get(i).getPayment() + ", " + 
								result2.get(i).getId() //거래 상대 아이디(seller)
								);
		}
	}
	
	@Test
	/** 회원간 거래내역 조회 테스트(read, 판매자=답변자 기준) */
	public void selectPayListAsSeller() {
		
		long sellerMemNum = 2;
		
		List<MemberAccount> result1 = memberAccountRepository.getPayListAsSeller(sellerMemNum);
		List<Member> result2 = memberRepository.getMemNumAsSeller(sellerMemNum);
		
		for(int i = 0; i <= result1.size()-1; i++) {
			System.out.println(result1.get(i).getRegDate() + ", " + 
					result1.get(i).getPayment() + ", " + 
					result2.get(i).getId() //거래 상대 아이디(buyer)
					);
		}
	}
	
	
}