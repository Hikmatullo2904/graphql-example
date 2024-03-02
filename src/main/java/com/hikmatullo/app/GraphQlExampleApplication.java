package com.hikmatullo.app;

import com.hikmatullo.app.entity.Post;
import com.hikmatullo.app.entity.User;
import com.hikmatullo.app.repo.PostRepository;
import com.hikmatullo.app.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class GraphQlExampleApplication {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(GraphQlExampleApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            User john = new User(1, "John", "Doe");
            User jane = new User(2, "Jane", "Doe");

            userRepository.saveAllAndFlush(List.of(john, jane));

            var johnPosts = List.of(
                    new Post( 1, "This is a post 1", "body",Rating.FIVE_STARS, john),
                    new Post( 2, "This is a post 2", "body",Rating.FOUR_STARS, john),
                    new Post( 3, "This is a post 3", "body",Rating.THREE_STARS, john)
            );

            postRepository.saveAllAndFlush(johnPosts);
        };
    }
}
