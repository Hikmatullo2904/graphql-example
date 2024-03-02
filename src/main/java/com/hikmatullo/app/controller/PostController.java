package com.hikmatullo.app.controller;

import com.hikmatullo.app.Rating;
import com.hikmatullo.app.dto.PostCreateDto;
import com.hikmatullo.app.entity.Post;
import com.hikmatullo.app.repo.PostRepository;
import com.hikmatullo.app.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable Integer id) {
        return postRepository.findById(id).orElse(null);
    }


    @SchemaMapping(typeName = "Query", value = "getPost")
    public Post getPost(@Argument Integer id) {

        return postRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @MutationMapping
    public Post createPost(@Argument PostCreateDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setBody(dto.getBody());
        post.setRating(Rating.FOUR_STARS);
        userRepository.findById(dto.getUserId()).ifPresent(post::setUser);
        return postRepository.save(post);
    }

}
