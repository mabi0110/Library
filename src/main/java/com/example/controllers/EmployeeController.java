package com.example.controllers;

import com.example.dao.PersonDao;
import com.example.models.Person;
import com.example.utils.RedirectUtil;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;

@ManagedBean
@SessionScoped
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
}
