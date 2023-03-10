package com.keduit.helloworld.repositoryTests;


import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.entity.Member;
import com.keduit.helloworld.repository.MemberRepository;

@SpringBootTest
public class MemberTests {

   @Autowired
   private MemberRepository repository;
   
   @Test
   public void insertMemberTest() {
      
      IntStream.rangeClosed(1, 30).forEach(i->{
         Member entity = Member.builder()
                          .id("user" + i)
                          .pw("1111")
                          .name("이름" + i)
                          .nickname("별명" + i)
                          .introduce("저는 " + i + "입니다 !!")
                          .email(i+"@abc.com")
                          .build();
         repository.save(entity);
      });
   }
   
   @Test
   public void selectMemberTest() {
      Optional<Member> result = repository.findById(2L);
      
      System.out.println(result);
   }
   
   @Test
   public void updateMemberTest() {
      
      Member entity = Member.builder().memberNum(2L)
                              .id("수정된아이디")
                              .pw("1234")
                              .name("수정한 이름1")
                              .nickname("수정한별명1")
                              .introduce("저는 동그라미입니다.")
                              .build();
      
      System.out.println(repository.save(entity));
      
   }
   
   @Test
   public void deleteMemberTest() {
      repository.deleteById(1L);
   }
   
   
}