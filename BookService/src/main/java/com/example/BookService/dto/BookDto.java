package com.example.BookService.dto;

import lombok.Data;

@Data
public class BookDto {
    private long id;
    private String isbn;
    private String title;
    private String genre;
    private String description;
    private String author;
}