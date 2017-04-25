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
public class Members {
    
    public int memberID;
    private String name,surname,mail,phonenumber,password,adress,departmant,faculty;

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

    public Members() {
    }

    public Members(int memberID, String name, String surname, String mail, String phonenumber, String password, String adress, String departmant, String faculty) {
        this.memberID = memberID;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phonenumber = phonenumber;
        this.password = password;
        this.adress = adress;
        this.departmant = departmant;
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Members{" + "memberID=" + memberID + ", name=" + name + ", surname=" + surname + ", mail=" + mail + ", phonenumber=" + phonenumber + ", password=" + password + ", adress=" + adress + ", departmant=" + departmant + ", faculty=" + faculty + '}';
    }
    
    
    
}
