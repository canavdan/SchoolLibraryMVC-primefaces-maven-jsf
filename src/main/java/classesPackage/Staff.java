/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesPackage;

/**
 *
 * @author Nemesis
 */
public class Staff {
      private int staffID;
    private String name, surname, mail, phonenumber, password,role;

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Staff() {
    }

    public Staff(int staffID, String name, String surname, String mail, String phonenumber, String password, String role) {
        this.staffID = staffID;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phonenumber = phonenumber;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff{" + "staffID=" + staffID + ", name=" + name + ", surname=" + surname + ", mail=" + mail + ", phonenumber=" + phonenumber + ", password=" + password + ", role=" + role + '}';
    }
    
    
}
