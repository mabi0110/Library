package com.example.controllers;

import com.example.BookNotExistsException;
import com.example.PersonNotExistException;
import com.example.dao.BookDao;
import com.example.dao.PersonDao;
import com.example.models.Book;
import com.example.models.Person;
import com.example.utils.RedirectUtil;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class EmployeeController {
    private String selectedUserFirstName;
    private String selectedUserLastName;
    private String selectedUserLogin;
    private String selectedUserPassword;
    private final String DEFAULT_ACCOUNT_TYPE = "USER";
    private final PersonDao personDao = new PersonDao();
    private final BookDao bookDao = new BookDao();
    private Person personToRegister;
    private Book bookToRegister;
    private Person personToRemove;
    private Book bookToRemove;
    private String selectedBookTitle;
    private String selectedBookISBN;
    private String selectedBookPublisher;
    private int selectedBookPublicationYear;

    private Person createPerson(){
        return new Person(
                selectedUserFirstName,
                selectedUserLastName,
                selectedUserLogin,
                selectedUserPassword,
                DEFAULT_ACCOUNT_TYPE);
    }

    private Book createBook() {
        return new Book(
                selectedBookTitle,
                selectedBookISBN,
                selectedBookPublisher,
                selectedBookPublicationYear);
    }
    public void addUser() throws IOException {
        personToRegister = createPerson();
        personDao.save(personToRegister);
        RedirectUtil.redirectToEmployeePage();
    }

    public void addBook() throws IOException {
        bookToRegister = createBook();
        bookDao.save(bookToRegister);
        RedirectUtil.redirectToEmployeePage();
    }

    public void removeUser() throws IOException {
        personToRemove = getPersonFromDb();
        personDao.remove(personToRemove.getId());
        RedirectUtil.redirectToEmployeePage();
    }

    public void removeBook() throws IOException {
        bookToRemove = getBookFromDb();
        bookDao.remove(bookToRemove.getId());
        RedirectUtil.redirectToEmployeePage();
    }

    private Person getPersonFromDb() {
        Optional<Person> personOptional = personDao.findUserWithFirstAndLastName(selectedUserFirstName, selectedUserLastName);
        return personOptional.orElseThrow(PersonNotExistException::new);
    }

    private Book getBookFromDb() {
        Optional<Book> bookOptional = bookDao.findBookWithTitle(selectedBookTitle);
        return bookOptional.orElseThrow(BookNotExistsException::new);
    }

    public List<Person> findUsers() {
        return personDao.getUsersFromDb();
    }


}
