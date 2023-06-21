package com.example.controllers;

import lombok.Getter;
import lombok.Setter;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
}
