package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class LibraryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // TODO: change the ID to String if needed

    @ManyToOne
    @JoinColumn(name = "library_id")
    @JsonBackReference
    private Library library;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;

    private int quantity;
}
