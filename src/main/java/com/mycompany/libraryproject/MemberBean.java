/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libraryproject;

import classesPackage.Members;
import classesPackage.PropertiesC;
import databasePackage.Database;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Properties;
import loginPackage.Util;

/**
 *
 * @author Nemesis
 */
@Named(value = "memberBean")
@SessionScoped
public class MemberBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public MemberBean() {
    }

    private int memberID;
    private String name, surname, mail, phonenumber, password, adress, departmant, faculty;

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDepartmant() {
        return departmant;
    }

    public void setDepartmant(String departmant) {
        this.departmant = departmant;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void fillVaribles() {
        PropertiesC properties = new PropertiesC();
        // properties.getUsername()
         //System.out.println("asdasd");
           // System.out.println(member);
        if (properties.getUsername().equals(Util.getUserName())) {
            Members member ;
            Database database = new Database();
            member = database.getUserInfo(Util.getUserName());
            //member = database.getUserInfo(properties.getUsername());
            setMemberID(Integer.parseInt(Util.getUserName()));
            setName(member.getName());
            setSurname(member.getSurname());
            setAdress(member.getAdress());
            setDepartmant(member.getDepartmant());
            setFaculty(member.getFaculty());
            setMail(member.getMail());
            setPhonenumber(member.getPhonenumber());
            setPassword(member.getPassword());
          
        } else {
            
            Members member = new Members();
            Database database = new Database();
            // member = database.getUserInfo(Util.getUserName());
            member = database.getUserInfo(properties.getUsername());
            setMemberID(Integer.parseInt(properties.getUsername()));
            setName(member.getName());
            setSurname(member.getSurname());
            setAdress(member.getAdress());
            setDepartmant(member.getDepartmant());
            setFaculty(member.getFaculty());
            setMail(member.getMail());
            setPhonenumber(member.getPhonenumber());
             setPassword(member.getPassword());
            
        }   
    }
  
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int takeDebt() {
        PropertiesC properties = new PropertiesC();
        Database database = new Database();
        if (database.hasPersonPenalty(properties.getUsername())) {
            amount = database.debtAmount(properties.getUsername());
            return amount;
        } else {
            return 0;
        }
    }

    public void clearDebt() {
        PropertiesC properties = new PropertiesC();
        Database database = new Database();
        database.clearDebt(properties.getUsername());
    }
    
    
    private boolean editmode;

public void edit() {
    editmode = true;
}

public void save() {
    Database database=new Database();
    database.changePassword(password,Util.getUserName());
    editmode = false;
}

public boolean isEditmode() {
    return editmode;
}
}
