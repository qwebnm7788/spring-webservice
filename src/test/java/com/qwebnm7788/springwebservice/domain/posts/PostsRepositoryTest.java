package com.qwebnm7788.springwebservice.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        postsRepository.save(Posts.builder()
                        .title("테스트 게시글")
                        .content("테스트 본문")
                        .author("qwebnm7788")
                        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 본문"));
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                        .title("테스트 게시글")
                        .content("테스트 본문")
                        .author("qwebnm7788")
                        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }

    @Test
    @Transactional
    public void 게시글목록_불러오기() {
        //given
        Posts posts1 = new Posts().builder().title("A").author("A").content("A").build();
        Posts posts2 = new Posts().builder().title("B").author("B").content("B").build();

        postsRepository.save(posts1);
        postsRepository.save(posts2);

        List<Posts> postsList = postsRepository.findAllDesc().collect(Collectors.toList());

        assertTrue(postsList.get(0).getTitle().equals("B"));
        assertTrue(postsList.get(1).getTitle().equals("A"));
    }
}