/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libraryproject;

import classesPackage.Members;
import databasePackage.Database;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Nemesis
 */
@Named(value = "userSearchResultBean")
@SessionScoped
public class UserSearchResultBean implements Serializable {

    public UserSearchResultBean() {
    }
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    public String search(){     
        Database database=new Database();
        memberList=database.getMembersSearch(query);
        return "usersearchresult";       
    }
    
    private List<Members> memberList;

    public List<Members> getMemberList() {
         Database database=new Database();
        memberList=database.getMembersSearch(query);
        return memberList;
    }

    public void setMemberList(List<Members> memberList) {
        this.memberList = memberList;
    }
    private Members selectionMember;

    public Members getSelectionMember() {
        return selectionMember;
    }

    public void setSelectionMember(Members selectionMember) {
        this.selectionMember = selectionMember;
    }
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Member Selected", ((Members) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        //System.out.println(((Members)event.getObject()).toString());
       
    }
     public String goUseroperation(){
          Properties prop = new Properties();
                OutputStream output = null;
                try {

                    output = new FileOutputStream("config.properties");
                    prop.setProperty("username", String.valueOf(selectionMember.getMemberID()));
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
        return "useroperation";
    }
    
    
}
