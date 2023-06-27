package com.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String pass;
    private String accountType;
    private List<Book> borrowedBooks = new ArrayList<>();

    private List<Book> reservedBooks = new ArrayList<>();

    public Person(String firstName, String lastName, String login, String pass, String accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.pass = pass;
        this.accountType = accountType;
    }

    public Person(Integer id, String firstName, String lastName, String login, String pass, String accountType) {
        this(firstName, lastName, login, pass, accountType);
        this.id = id;
    }
}
