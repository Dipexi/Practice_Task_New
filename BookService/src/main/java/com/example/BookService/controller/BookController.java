package com.example.BookService.controller;

import com.example.BookService.dto.BookDto;
import com.example.BookService.model.Book;
import com.example.BookService.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks().stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") long id) {
        Book book = bookService.findBookById(id);
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable("isbn") String isbn) {
        Book book = bookService.findBookByIsbn(isbn);
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        Book newBook = bookService.saveBook(book);
        BookDto newBookDto = modelMapper.map(newBook, BookDto.class);
        return ResponseEntity.status(201).body(newBookDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") long id, @RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        Book updatedBook = bookService.updateBookDetails(id, book);
        BookDto updatedBookDto = modelMapper.map(updatedBook, BookDto.class);
        return ResponseEntity.ok(updatedBookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
