package com.example.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
public class RedirectUtil {
    public static final String GENERAL_PAGES = "/generalPages";
    public static final String INDEX_PAGE = "/index.xhtml";
    public static final String LOGIN_FORM = "/loginForm.xhtml";
    public static final String REGISTER_FORM = "/registerForm.xhtml";
    public static final String EMPLOYEE_PAGES = "/employeePages";
    public static final String USER_PAGES = "/userPages";
    private static final String USER_MAIN_PAGE = "/userMainPage.xhtml";
    private static final String EMPLOYEE_MAIN_PAGE = "/employeeMainPage.xhtml";


    public static void redirectToIndexPage() throws IOException {
        redirect(GENERAL_PAGES + INDEX_PAGE);
    }

    public static void redirectToLoginForm() throws IOException {
        redirect(GENERAL_PAGES + LOGIN_FORM);
    }

    public static void redirectToRegisterForm() throws IOException {
        redirect(GENERAL_PAGES + REGISTER_FORM);
    }

    public static void redirectToUserPage() throws IOException {
        redirect(USER_PAGES + USER_MAIN_PAGE);
    }

    public static void redirectToEmployeePage() throws IOException {
        redirect(EMPLOYEE_PAGES + EMPLOYEE_MAIN_PAGE);
    }
    
    private static void redirect(String path) throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + path);
    }



}
