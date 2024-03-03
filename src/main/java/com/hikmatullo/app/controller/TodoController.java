package com.hikmatullo.app.controller;

import com.hikmatullo.app.dto.TodoDto;
import com.hikmatullo.app.entity.Todo;
import com.hikmatullo.app.repo.TodoRepository;
import com.hikmatullo.app.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;


    @QueryMapping
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @QueryMapping
    public Todo getTodo(@Argument Integer id) {
        return todoRepository.findById(id).orElse(null);
    }



    @MutationMapping
    public Todo createTodo(@Argument(value = "dto") TodoDto todoDto) {
        Todo todo = new Todo();
        setDtoToEntity(todoDto, todo);
        return todoRepository.save(todo);
    }

    @MutationMapping
    public String updateTodo(@Argument Integer id, @Argument(value = "dto") TodoDto todoDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("No todo found with id: " + id));
        setDtoToEntity(todoDto, todo);
        return "updated";
    }

    private void setDtoToEntity(TodoDto todoDto, Todo todo) {
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCategory(todoDto.getCategory());
        todo.setLevel(todoDto.getLevel());
        todo.setDeadLine(todoDto.getDeadLine());
        todo.setCompleted(todoDto.isCompleted());
        todo.setUser(userRepository.findById(todoDto.getUserId()).orElse(null));
        todoRepository.save(todo);
    }

}
