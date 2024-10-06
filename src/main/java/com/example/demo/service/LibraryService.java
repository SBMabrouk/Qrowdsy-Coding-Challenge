package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Library;
import com.example.demo.model.LibraryBook;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryBookRepository;
import com.example.demo.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final LibraryBookRepository libraryBookRepository;
    private final BookRepository bookRepository;

    public LibraryService(LibraryRepository libraryRepository, LibraryBookRepository libraryBookRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.libraryBookRepository = libraryBookRepository;
        this.bookRepository = bookRepository;
    }

    public Library saveLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public LibraryBook addBookToLibrary(Long libraryId, Long bookId, int quantity) {
        // TODO: add Optional.nullable
        Library library = libraryRepository.findById(libraryId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        LibraryBook libraryBook = new LibraryBook();
        libraryBook.setLibrary(library);
        libraryBook.setBook(book);
        libraryBook.setQuantity(quantity);
        return libraryBookRepository.save(libraryBook);
    }

    public List<Book> getBooksInLibrary(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow();
        return libraryBookRepository.findByLibrary(library)
                .stream()
                .map(LibraryBook::getBook)
                .collect(Collectors.toList());
    }

    public List<String> getGenresInLibrary(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow();
        return libraryBookRepository.findByLibrary(library)
                .stream()
                .map(libraryBook -> libraryBook.getBook().getGenre())
                .distinct()
                .collect(Collectors.toList());
    }
}
