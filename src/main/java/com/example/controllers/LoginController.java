package com.example.controllers;

import com.example.dao.PersonDao;
import com.example.models.Person;
import com.example.utils.RedirectUtil;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.util.Optional;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class LoginController {

    private String selectedLogin;
    private String selectedPassword;
    private Person loggedPerson;
    private final PersonDao personDao = new PersonDao();

    public void login() throws IOException {
        loggedPerson = getPersonFromDb();
        if ((("USER").equals(loggedPerson.getAccountType()))) {
            RedirectUtil.redirectToUserPage();
        } else if ((("EMPLOYEE").equals(loggedPerson.getAccountType()))){
            RedirectUtil.redirectToEmployeePage();
        }
    }
    private Person getPersonFromDb() {
        Optional<Person> personOptional = personDao.findPersonWithLoginAndPassword(selectedLogin, selectedPassword);
        return personOptional.orElse(null);
    }
}
