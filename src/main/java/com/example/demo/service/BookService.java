package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setName(bookDetails.getName());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setGenre(bookDetails.getGenre());
                    book.setReleaseDate(bookDetails.getReleaseDate());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id)
                .ifPresentOrElse(bookRepository::delete,
                        () -> {
                            throw new RuntimeException("Book not found with id " + id);
                        });
    }
}
