/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libraryproject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
import loginPackage.LoginDAO;
import loginPackage.Util;

/**
 *
 * @author Nemesis
 */
@Named(value = "loginnBean")
@SessionScoped
public class loginnBean implements Serializable {

    /**
     * Creates a new instance of loginnBean
     */
    public loginnBean() {
    }
    private String username = "";
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String validateUsernamePassword() {
        boolean valid2 = LoginDAO.validate(username, password);
        boolean valid = false;
        if (valid2) {
            valid = true;
        } else {
            valid = LoginDAO.validate2(username, password);
        }
        /* System.out.println("AAAA");
        System.out.println(username);
        System.out.println(password);
        System.out.println("AAAA");*/
        if (valid) {
             Properties prop = new Properties();
                OutputStream output = null;
                try {

                    output = new FileOutputStream("config.properties");
                    prop.setProperty("username",username);
                    prop.store(output, null);

                } catch (IOException io) {
                    io.printStackTrace();
                } finally {
                    if (output != null) {
                        try {
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            // int role = LoginDAO.validate2(username, password);
            HttpSession session = Util.getSession();
            session.setAttribute("username", username);
            //session.setAttribute(LoginDAO.validate3(username, password), role);
            if ((LoginDAO.validate3(username, password)).equals("admin")) {
                return "adminindex";
            } else if ((LoginDAO.validate3(username, password)).equals("student")
                    || (LoginDAO.validate3(username, password)).equals("teacher")
                    || (LoginDAO.validate3(username, password)).equals("masters")) {

                return "memberindex";
            } else {
               
                return "moderatorindex";

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "wrongmyaccount";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "index";
    }

    public void logout2() {
        HttpSession session = Util.getSession();
        session.invalidate();
    }

    public void isAdmin(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (!(LoginDAO.validate3(username, password)).equals("admin")) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("unauthorized");
        }
        if (!"admin".equals(fc.getExternalContext().getSessionMap().get("role"))) {
            /* ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

            nav.performNavigation("unauthorized");*/
        }
    }

    public void isMember(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (!(LoginDAO.validate3(username, password)).equals("student")
                && !(LoginDAO.validate3(username, password)).equals("teacher")
                && !(LoginDAO.validate3(username, password)).equals("masters")
                && !(LoginDAO.validate3(username, password)).equals("moderator")) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("unauthorized");
        }
        if (!"admin".equals(fc.getExternalContext().getSessionMap().get("role"))) {
            /* ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

            nav.performNavigation("unauthorized");*/
        }
    }

    public void isLogin(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (!LoginDAO.validate(username, password)) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("myaccount");
        } else if ((LoginDAO.validate3(username, password)).equals("admin")) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("adminindex");
        } else if ((LoginDAO.validate3(username, password)).equals("student")
                || (LoginDAO.validate3(username, password)).equals("teacher")
                || (LoginDAO.validate3(username, password)).equals("masters")) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("memberindex");
        } else {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("moderatorindex");
        }

    }

    public void isModerator(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (!(LoginDAO.validate3(username, password)).equals("moderator")) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("unauthorized");
        }
        if (!"moderator".equals(fc.getExternalContext().getSessionMap().get("role"))) {
            /* ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

            nav.performNavigation("unauthorized");*/
        }
    }
}
