package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.request.BookIdRequest;
import com.example.demo.model.request.UpdateBookRequest;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/get")
    public ResponseEntity<Book> getBookById(@RequestBody BookIdRequest request) {
        return bookService.getBookById(request.getId())
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody UpdateBookRequest request) {
        Book updatedBook = bookService.updateBook(request.getId(), request.getBookDetails());
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBook(@RequestBody BookIdRequest request) {
        bookService.deleteBook(request.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
