package com.astrapay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequestDto {
    @NotBlank(message = "Title is mandatory")
    @Size(max = 50, message = "Title cannot be longer than 50 characters")
    private String title;

    @NotBlank(message = "Content is mandatory")
    @Size(max = 500, message = "Content cannot be longer than 500 characters")
    private String content;
}
