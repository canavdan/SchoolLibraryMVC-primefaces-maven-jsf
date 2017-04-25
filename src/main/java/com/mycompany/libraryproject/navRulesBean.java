/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libraryproject;

import databasePackage.Database;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Nemesis
 */
@Named(value = "navRulesBean")
@SessionScoped
public class navRulesBean implements Serializable {

    /**
     * Creates a new instance of navRulesBean
     */
    public navRulesBean() {
    }
    public String goIndex(){
        return "index";
    }
    public String goLibarycollection(){
        return "libarycollection";
    }
    public String goMyaccount(){
        return "myaccount";
    }
    public String goNewmaterials(){
        return "newmaterials";
    }
    public String goSearch(){
        return "search";
    }
    public String goRequests(){
        return "requests";
    }
    public String goUseroperation(){
        return "useroperation";
    }
    
    public void check(){
        Database database=new Database();
        database.controlDuetoDate();
    }
    
}
