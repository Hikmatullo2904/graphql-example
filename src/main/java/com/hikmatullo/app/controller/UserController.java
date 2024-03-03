package com.hikmatullo.app.controller;

import com.hikmatullo.app.dto.UserDto;
import com.hikmatullo.app.entity.User;
import com.hikmatullo.app.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @MutationMapping(value = "createUser")
    public User createUser(@Argument(value = "dto") UserDto userDto) {
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument Integer id, @Argument(value = "dto") UserDto userDto) {
        User user = userRepository.findById(id).orElse(null);
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    @QueryMapping
    public User getUser(@Argument Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }


    @SchemaMapping(typeName = "Mutation", value = "deleteUser")
    public String deleteUser(@Argument Integer id) {
        userRepository.deleteById(id);
        return "deleted";
    }

}
