package com.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private Integer id;
    private String title;
    private String isbn;
    private String publisher;
    private int publicationYear;

    public Book(String title, String isbn, String publisher, int publicationYear) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }

    public Book(Integer id, String title, String isbn, String publisher, int publicationYear) {
        this(title, isbn, publisher, publicationYear);
        this.id = id;
    }

}
