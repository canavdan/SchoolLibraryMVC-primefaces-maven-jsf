/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libraryproject;

import classesPackage.Materials;
import databasePackage.Database;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Size;

/**
 *
 * @author Nemesis
 */
@Named(value = "searchBean")
@SessionScoped
public class searchBean implements Serializable {


    public searchBean() {
    }  
     @Size(min=1,max=100)
     private String query;
     private String catalog,language,branch,index;

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
     
     Database database=new Database();
    public List<String> getCatalogNames() { 
        return database.catalogNames();
    }
    public List<String> getBranchNames() {
        return database.branchNames();
    }

    public List<String> getLangNames() {
        return database.langNames();
    }
    public String search(){
      // searchResultBean s1=new searchResultBean();
      // s1.getUserchoose(catalog, branch, language, index,query);
        return "searchresult";
    }
    public String searchUser(){
      // searchResultBean s1=new searchResultBean();
      // s1.getUserchoose(catalog, branch, language, index,query);
        return "takebook";
    }
    public void reset(){
        setBranch("allBranch");
        setCatalog("allCatalog");
        setLanguage("allLang");
        setQuery(null);
        setIndex("keywords");
    }
    
    private List<Materials> material;

    public List<Materials> getMaterial() {
        // material=getUserchoose(type, branch, lang, index, query);
        Database databasee = new Database();
        material = databasee.searchSqlCommand(catalog, branch, language, index, query);
        return material;

    }

    public void setMaterial(List<Materials> material) {
        this.material = material;
    }
    private Materials selectedMaterials;

    public Materials getSelectedMaterials() {     
        return selectedMaterials;
    }

    public void setSelectedMaterials(Materials selectedMaterials) {
        this.selectedMaterials = selectedMaterials;
    }
     
    
   
    
    
     
}
