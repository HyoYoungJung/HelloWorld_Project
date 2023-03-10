package com.keduit.helloworld.repositoryTests;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.entity.Member;
import com.keduit.helloworld.entity.PointAccount;
import com.keduit.helloworld.repository.PointAccountRepository;

@SpringBootTest
public class PointAccountRepositoryTests {

	@Autowired
	private PointAccountRepository repository;
	
	@Test
	/** 포인트 거래내역 등록 테스트(create) */
	public void insertPoint() {
		
		IntStream.rangeClosed(1, 1000).forEach(i -> {
			long num = (long) (Math.random()*50)+1;
			long charge = (long) (Math.random()*50)*10000+1;
			long exchange = (long) (Math.random()*50)*10000+1;
			if(charge > exchange)exchange = 0;
			else charge = 0;
			PointAccount entity = PointAccount
					.builder()
					.charge(charge)
					.balance(10000L*i)
					.exchange(exchange)
					.memberNum(num)
					.build();
			repository.save(entity);
		});
	}
		
	@Test
	/** 포인트 거래내역 조회(read) */
	public void selectPointList() {
		
		List<PointAccount> result = repository.getPointAccount(1L);
		
		for(PointAccount i : result) {
			System.out.println(i);
			
//			System.out.println(i.getRegDate() + ", " +
//					   i.getCharge() + ", " +
//					   i.getExchange() + ", " +
//					   i.getBalance());
		}
	}
	
	
}