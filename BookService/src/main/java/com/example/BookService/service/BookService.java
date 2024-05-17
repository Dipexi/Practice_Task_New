package com.example.BookService.service;

import com.example.BookService.model.Book;
import com.example.BookService.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    public void deleteBookById(long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }

    public Book updateBookDetails(long id, Book book) {
        Book existingBook = findBookById(id);
        existingBook.setIsbn(book.getIsbn());
        existingBook.setTitle(book.getTitle());
        existingBook.setGenre(book.getGenre());
        existingBook.setDescription(book.getDescription());
        existingBook.setAuthor(book.getAuthor());
        return bookRepository.save(existingBook);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}