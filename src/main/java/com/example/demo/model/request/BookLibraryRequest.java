package com.example.demo.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookLibraryRequest {
    private Long libraryId;
    private Long bookId;
    private int quantity;

}