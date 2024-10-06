package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.request.BookLibraryRequest;
import com.example.demo.model.request.LibraryQueryRequest;
import com.example.demo.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<Library> addLibrary(@RequestBody Library library) {
        return ResponseEntity.ok(libraryService.saveLibrary(library));
    }

    @PostMapping("/books/addBook")
    public ResponseEntity<LibraryBook> addBookToLibrary(@RequestBody BookLibraryRequest request) {
        return ResponseEntity.ok(libraryService.addBookToLibrary(request.getLibraryId(), request.getBookId(), request.getQuantity()));
    }

    @GetMapping("/books/getBooks")
    public ResponseEntity<List<Book>> getBooksInLibrary(@RequestBody LibraryQueryRequest request) {
        return ResponseEntity.ok(libraryService.getBooksInLibrary(request.getLibraryId()));
    }

    @GetMapping("/genres/getGenres")
    public ResponseEntity<List<String>> getGenresInLibrary(@RequestBody LibraryQueryRequest request) {
        return ResponseEntity.ok(libraryService.getGenresInLibrary(request.getLibraryId()));
    }
}