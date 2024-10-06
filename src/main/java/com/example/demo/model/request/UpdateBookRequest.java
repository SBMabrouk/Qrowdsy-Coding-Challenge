package com.example.demo.model.request;

import com.example.demo.model.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateBookRequest {
    private Long id;
    private Book bookDetails;
}
