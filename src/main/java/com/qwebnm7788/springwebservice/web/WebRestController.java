package com.qwebnm7788.springwebservice.web;

import com.qwebnm7788.springwebservice.domain.posts.PostsRepository;
import com.qwebnm7788.springwebservice.domain.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsRepository postsRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto) {
        postsRepository.save(dto.toEntity());
    }
}
