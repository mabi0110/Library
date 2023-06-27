package com.example.controllers;

import com.example.PersonNoExistException;
import com.example.dao.PersonDao;
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
    private Person personToRegister;
    private Person personToRemove;

    private Person createPerson(){
        return new Person(selectedUserFirstName,
                selectedUserLastName,
                selectedUserLogin,
                selectedUserPassword,
                DEFAULT_ACCOUNT_TYPE);
    }
    public void addUser() throws IOException {
        personToRegister = createPerson();
        personDao.save(personToRegister);
        RedirectUtil.redirectToEmployeePage();
    }

    public void removeUser() throws IOException {
        personToRemove = getPersonFromDb();
        System.out.println(personToRemove);
        personDao.remove(personToRemove.getId());
        RedirectUtil.redirectToEmployeePage();
    }

    private Person getPersonFromDb() {
        Optional<Person> personOptional = personDao.findUserWithFirstAndLastName(selectedUserFirstName, selectedUserLastName);
        return personOptional.orElseThrow(PersonNoExistException::new);
    }

    public List<Person> findUsers(){
        return personDao.findUsers();
    }
}
