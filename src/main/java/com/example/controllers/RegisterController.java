package com.example.controllers;

import com.example.dao.PersonDao;
import com.example.models.Person;
import com.example.utils.RedirectUtil;
import lombok.Getter;
import lombok.Setter;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class RegisterController {
    private String selectedFirstName;
    private String selectedLastName;
    private String selectedLogin;
    private String selectedPass;
    private String selectedAccountType;
    private final PersonDao personDao = new PersonDao();
    private Person personToRegister;

    private Person createPerson(){
        return new Person(selectedFirstName, selectedLastName, selectedLogin, selectedPass, selectedAccountType);
    }
    public void register() throws IOException {
        personToRegister = createPerson();
        personDao.save(personToRegister);
        RedirectUtil.redirectToLoginForm();
    }
}
