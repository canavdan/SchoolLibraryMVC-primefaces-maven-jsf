/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libraryproject;

import classesPackage.Loan;
import classesPackage.Materials;
import classesPackage.Members;
import classesPackage.PropertiesC;
import databasePackage.Database;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import loginPackage.Util;

/**
 *
 * @author Nemesis
 */
@Named(value = "takeBookBean")
@SessionScoped
public class TakeBookBean implements Serializable {

    /**
     * Creates a new instance of TakeBookBean
     */
    public TakeBookBean() {
    }
    private Materials selectedMaterials;

    public Materials getSelectedMaterials() {
        return selectedMaterials;
    }
    private String query;
    private String catalog, language, branch, index;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    private List<String> catalogNames;
    private List<String> branchNames;
    private List<String> langNames;

    Database database = new Database();

    public List<String> getCatalogNames() {
        return database.catalogNames();
    }

    public List<String> getBranchNames() {
        return database.branchNames();
    }

    public List<String> getLangNames() {
        return database.langNames();
    }

    public void setSelectedMaterials(Materials selectedMaterials) {
        this.selectedMaterials = selectedMaterials;
    }
    private List<Materials> material;

    public List<Materials> getMaterial() {
        // material=getUserchoose(type, branch, lang, index, query);
        List<Materials> material2;
        Database database = new Database();
        material2 = database.searchSqlCommand(catalog, branch, language, index, query);
        material = database.getAvaibleMaterials(material2);
        return material;

    }

    public void reset() {
        setBranch("allBranch");
        setCatalog("allCatalog");
        setLanguage("allLang");
        setQuery(null);
        setIndex("keywords");
    }

    public void setMaterial(List<Materials> material) {
        this.material = material;
    }
    private int username;

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String takeMaterial(Materials m) {
        PropertiesC properties=new PropertiesC();
       // System.out.println("Username:::" +properties.getUsername());
        Database database2 = new Database();
        if (Util.getUserName().equals(properties.getUsername())) {
            if (database2.hasPersonPenalty(Util.getUserName()) || database2.hasUserAbletoTake(Util.getUserName())) {
                System.out.println("You have a penalty.You can not return the book.");
                // addMessage("You have a penalty.You can not return the book.");   
                return "memberindex";
            } else {
                //database2.takeMaterial(m,Util.getUserName());
                database2.addtoLoan(m, Util.getUserName());
                database2.setAvaibleZero(m, Util.getUserName());
                return "memberindex";
            }
        } else 
        {
            if (database2.hasPersonPenalty(properties.getUsername()) || database2.hasUserAbletoTake(properties.getUsername())) {
            System.out.println("You have a penalty.You can not return the book.");
            // addMessage("You have a penalty.You can not return the book.");   
            return "usersearchresult";
        } else {
            //database2.takeMaterial(m,Util.getUserName());
            database2.addtoLoan(m, properties.getUsername());
            database2.setAvaibleZero(m, properties.getUsername());
            return "usersearchresult";

        }
    }

    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    private List<Loan> UserMaterialss;

    public List<Loan> getUserMaterialss() {
       // System.out.println(String.valueOf(username));
        //System.out.println(use);
        PropertiesC properties=new PropertiesC();
        Database database2 = new Database();
        if (Util.getUserName().equals(properties.getUsername())) {
            UserMaterialss = database2.getUserMaterials(Util.getUserName());
            
        } else {
            System.out.println(properties.getUsername());
            UserMaterialss = database2.getUserMaterials(properties.getUsername());
        }
        return UserMaterialss;
    }

    public void setUserMaterialss(List<Loan> UserMaterialss) {
        this.UserMaterialss = UserMaterialss;
    }

    public String returnMaterial(Loan loan) {
        PropertiesC properties=new PropertiesC();
        Database database2 = new Database();
        if(Util.getUserName().equals(properties.getUsername()))
        {
           
             if (database2.hasPersonPenalty(Util.getUserName())) {
            addMessage("You have a penalty.You can not return the book.");
        } else {
           database.addLoanHistory(loan);
                 database2.returnBook(loan);
        }
               return "memberindex";
        }
        else{
           
            if (database2.hasPersonPenalty(properties.getUsername())) {
            addMessage("You have a penalty.You can not return the book.");
        } else {
            database.addLoanHistory(loan);
                database2.returnBook(loan);
        } 
            return "useroperation";
        } 
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
         if(number>1)
            System.out.println("Max number.We cant increase");
        else
            database.increaseExtend(loan.getLoanID(),number+1,outdate2);
        return "useroperation";
    }
 
    
}
