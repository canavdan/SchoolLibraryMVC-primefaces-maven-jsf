/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libraryproject;

import classesPackage.Loan;
import classesPackage.PropertiesC;
import databasePackage.Database;
//import java.sql.Date;
import java.util.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import loginPackage.Util;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Nemesis
 */
@Named(value = "loanBean")
@Dependent
public class LoanBean {

    public LoanBean() {
    }
    private int loanID, extend;
    private Date out, due;
    private String materielName;

    private int username;

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public int getExtend() {
        return extend;
    }

    public void setExtend(int extend) {
        this.extend = extend;
    }

    public Date getOut() {
        return out;
    }

    public void setOut(Date out) {
        this.out = out;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }
    private String use;

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    private List<Loan> UserMaterialss;

    public List<Loan> getUserMaterialss() {
        // System.out.println(String.valueOf(username));
        // System.out.println(use);
        PropertiesC properties = new PropertiesC();

        Database database = new Database();
        if (Util.getUserName().equals(properties.getUsername())) {
            UserMaterialss = database.getUserMaterials(Util.getUserName());

        } else {
            System.out.println(String.valueOf(username));
            UserMaterialss = database.getUserMaterials(properties.getUsername());
        }
        /* if (Util.getUserName().equals(String.valueOf(username))) {
            UserMaterialss = database.getUserMaterials(Util.getUserName());
            
        } else {
            System.out.println(String.valueOf(username));
            UserMaterialss = database.getUserMaterials(String.valueOf(username));
        }*/
        return UserMaterialss;
    }

    public void setUserMaterialss(List<Loan> UserMaterialss) {
        this.UserMaterialss = UserMaterialss;
    }

    public String returnMaterial(Loan loan) {
        PropertiesC properties = new PropertiesC();
        Database database = new Database();
        if (Util.getUserName().equals(properties.getUsername())) {
            if (database.hasPersonPenalty(Util.getUserName())) {
                addMessage("You have a penalty.You can not return the book.");
            } else {
                 database.addLoanHistory(loan);
                database.returnBook(loan);
               
            }
        } else if (database.hasPersonPenalty(properties.getUsername())) {
            addMessage("You have a penalty.You can not return the book.");
        } else {
            database.addLoanHistory(loan);
            database.returnBook(loan);
             
        }

        return "memberindex";
    }

    public void attrListener(ActionEvent event) {
        setUsername((Integer) event.getComponent().getAttributes().get("username"));

    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String extendBook(Loan loan) {
        Database database=new Database();
        System.out.println(loan.getLoanID()+"asdasd");
        int number;
        number=database.canIncrease(loan.getLoanID());
        Date outdate = loan.getDue();
        //System.out.println(outdate);
        Calendar c = Calendar.getInstance();
        c.setTime(outdate);
        c.add(Calendar.DATE, 30);
       java.sql.Date outdate2 = new java.sql.Date(c.getTime().getTime());
        System.out.println(outdate2);        
         if(number>1){
             showMessageExtends() ;
                      System.out.println("Max number.We cant increase");
         }
   
         else if(database.hasPersonPenalty(Util.getUserName())){
             showMessagePenalty();
             System.out.println("You have a penalty.You cant return the book");
         }
        else
            database.increaseExtend(loan.getLoanID(),number+1,outdate2);
        return "memberindex";
    }
    
    public void showMessagePenalty() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "You have a penalty");
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    public void showMessageExtends() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "You cant extend");
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
}
