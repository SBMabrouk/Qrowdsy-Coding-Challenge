package com.example.demo.repository;


import com.example.demo.model.LibraryBook;
import com.example.demo.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
    List<LibraryBook> findByLibrary(Library library);
}
