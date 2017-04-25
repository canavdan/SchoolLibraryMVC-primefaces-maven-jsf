/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libraryproject;

import classesPackage.LoanHistory;
import classesPackage.Materials;
import classesPackage.PropertiesC;
import databasePackage.Database;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import loginPackage.Util;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Nemesis
 */
@ManagedBean(name = "MaterialsBean")
@SessionScoped
public class MaterialsBean implements Serializable {

    public MaterialsBean() {
    }
    private List<Materials> material;
    private List<Materials> lastestMaterial;

    public List<Materials> getLastestMaterial() throws SQLException {
        Database database = new Database();
        lastestMaterial = database.getLastestMaterialsFromDatabase();
        return lastestMaterial;
    }

    public void setLastestMaterial(List<Materials> lastestMaterial) {
        this.lastestMaterial = lastestMaterial;
    }

    public List<Materials> getMaterial() throws SQLException {
        Database database = new Database();
        material = database.getMaterialsFromDatabase();
        //  System.out.println("TryBean22");
        // System.out.println(material.get(5).getName());
        //  System.out.println(material.get(12).getName());
        return material;
    }

    public void setMaterial(ArrayList<Materials> material) {
        this.material = material;
    }

    /*public List<Materials> takeMaterials(){
        return database.getMaterialsFromDatabase();
    }*/
    private Materials selectedMaterials;

    public Materials getSelectedMaterials() {
        return selectedMaterials;
    }

    public void setSelectedMaterials(Materials selectedMaterials) {
        this.selectedMaterials = selectedMaterials;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Materials Selected", ((Materials) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Materials Unselected", ((Materials) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private int materielID, avaible, pageNo,price;
    private String name,password,title,  description, type, lang, publicher, branch, categories, author;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaterielID() {
        return materielID;
    }

    public void setMaterielID(int materielID) {
        this.materielID = materielID;
    }

    public int getAvaible() {
        return avaible;
    }

    public void setAvaible(int avaible) {
        this.avaible = avaible;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPublicher() {
        return publicher;
    }

    public void setPublicher(String publicher) {
        this.publicher = publicher;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private List<String> categoriesNames;
    private List<String> branchNames;
    private List<String> langNames;
    private List<String> typeNames;
    Database database = new Database();

    public List<String> getCategoriesNames() {
        return database.categoriesNames();
    }


    public List<String> getBranchNames() {
        return database.branchNames();
    }

    public List<String> getLangNames() {
        return database.langNames();
    }

    public List<String> getTypeNames() {
        return database.typeNames();
    }

    public void reset() {
        setAuthor(null);
        setPrice(Integer.valueOf(""));
        setTitle(null);
        setDescription(null);
        setName(null);
        setPageNo(Integer.valueOf(""));
        setMaterielID(Integer.valueOf(""));
    }

    public String add() {
        Materials materials = new Materials();
        // materials.set
        database.addBook(name, title, description, type, categories,lang,branch,price,pageNo, materielID, publicher);
        return "moderatorindex";

    }
    private List<LoanHistory> historyM;

    public List<LoanHistory> getHistoryM() {
        PropertiesC properties = new PropertiesC();
        historyM=database.getHistoryM(properties.getUsername());
        return historyM;
    }

    public void setHistoryM(List<LoanHistory> historyM) {
        this.historyM = historyM;
    }
    
}
