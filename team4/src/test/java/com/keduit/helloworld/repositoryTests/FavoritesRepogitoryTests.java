package com.keduit.helloworld.repositoryTests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.helloworld.dto.FavoritesDTO;
import com.keduit.helloworld.entity.Favorites;
import com.keduit.helloworld.entity.Member;
import com.keduit.helloworld.repository.FavoritesRepository;
import com.keduit.helloworld.repository.MemberRepository;

@SpringBootTest
public class FavoritesRepogitoryTests {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {
        HashMap<Long, List<Long>> user = new HashMap<>();

        IntStream.rangeClosed(1, 200).forEach(i -> {
            long num = (long) Math.random() * 50;
            if (num != 25) {
                if (!user.containsKey(num)) {
                    ArrayList<Long> arr = new ArrayList<>();
                    user.put(num, arr);
                }

                List<Long> temp = user.get(num);
                if (!temp.contains(num - 50)) {
                    temp.add(num);
                    long a = (long) (num) + 1;
                    long b = (long) (50 - num) + 1;

                    Favorites entity = Favorites
                            .builder()
                            .bookmarked(a)
                            .bookmarker(b)
                            .build();
                    favoritesRepository.save(entity);
                }
            }
        });
    }

    @Test
    public void selectTest() {

        List<Member> member = memberRepository.getMemberMarker(1L);


        for (Member i : member) {
            System.out.println(i.getId() + " , " + i.getNickname());

        }
    }


}