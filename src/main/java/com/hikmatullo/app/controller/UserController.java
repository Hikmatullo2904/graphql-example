package com.hikmatullo.app.controller;

import com.hikmatullo.app.dto.UserUpdateDto;
import com.hikmatullo.app.dto.UsersDto;
import com.hikmatullo.app.entity.User;
import com.hikmatullo.app.repo.PostRepository;
import com.hikmatullo.app.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping
    public List<UsersDto> getAll() {
        List<User> all = userRepository.findAll();
       return all.stream()
                .map(user -> {
                    var dto = new UsersDto(user.getId(), user.getFirstName(), user.getLastName());
                    dto.setPosts(postRepository.findByUserId(user.getId()));
                    return dto;
                }).toList();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable Integer id, @RequestBody UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return;
        }
        if(Objects.nonNull(userUpdateDto.getFirstName()))
            user.setFirstName(userUpdateDto.getFirstName());
        if(Objects.nonNull(userUpdateDto.getLastName()))
            user.setLastName(userUpdateDto.getLastName());
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
