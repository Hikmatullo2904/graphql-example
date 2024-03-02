package com.hikmatullo.app.entity;

import com.hikmatullo.app.entity.enums.Category;
import com.hikmatullo.app.entity.enums.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Category category;
    private Level level;
    private LocalDate deadLine;
    private boolean completed;

    @ManyToOne
    private User user;
}