package com.fmajorcminor.libraryapi.dto.book;

import java.time.LocalDate;

import com.fmajorcminor.libraryapi.dto.author.AuthorRequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BookRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotBlank(message = "Author is required")
    private AuthorRequestDTO author;
    @NotNull(message = "Please provide a published date")
    private LocalDate publishedDate;
}
