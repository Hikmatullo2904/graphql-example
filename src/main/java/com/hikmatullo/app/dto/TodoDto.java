package com.hikmatullo.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hikmatullo.app.entity.enums.Category;
import com.hikmatullo.app.entity.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private String title;
    private String description;
    private Category category;
    private Level level;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deadLine;

    private boolean completed;
    private Integer userId;
}
