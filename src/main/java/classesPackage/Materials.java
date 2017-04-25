/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesPackage;

public class Materials {

    private int materielID, type_ID, language_ID, publicher_ID, categories_ID, branch_ID, avaible, pageNo;

    private String name, title, price, description, type, lang, publicher, branch, categories,authors;

    public Materials() {
    }

    public Materials(int materielID, int avaible, int pageNo, String name, String title, String description, String type, String lang, String publicher, String branch, String categories) {
        this.materielID = materielID;
        this.avaible = avaible;
        this.pageNo = pageNo;
        this.name = name;
        this.title = title;
        this.description = description;
        this.type = type;
        this.lang = lang;
        this.publicher = publicher;
        this.branch = branch;
        this.categories = categories;
    }

    public Materials(int materielID, int avaible, int pageNo, String name, String title, String price, String description, String type, String lang, String publicher, String branch, String categories) {
        this.materielID = materielID;
        this.avaible = avaible;
        this.pageNo = pageNo;
        this.name = name;
        this.title = title;
        this.price = price;
        this.description = description;
        this.type = type;
        this.lang = lang;
        this.publicher = publicher;
        this.branch = branch;
        this.categories = categories;
    }

    public Materials(int materielID, int type_ID, int language_ID, int publicher_ID, int categories_ID, int branch_ID) {
        this.materielID = materielID;
        this.type_ID = type_ID;
        this.language_ID = language_ID;
        this.publicher_ID = publicher_ID;
        this.categories_ID = categories_ID;
        this.branch_ID = branch_ID;
    }

    public Materials(int materielID, int type_ID, int language_ID, int publicher_ID, int categories_ID, int branch_ID, int avaible, int pageNo, String name, String title, String price, String description) {
        this.materielID = materielID;
        this.type_ID = type_ID;
        this.language_ID = language_ID;
        this.publicher_ID = publicher_ID;
        this.categories_ID = categories_ID;
        this.branch_ID = branch_ID;
        this.avaible = avaible;
        this.pageNo = pageNo;
        this.name = name;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
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

    public int getMaterielID() {
        return materielID;
    }

    public void setMaterielID(int materielID) {
        this.materielID = materielID;
    }

    public int getType_ID() {
        return type_ID;
    }

    public void setType_ID(int type_ID) {
        this.type_ID = type_ID;
    }

    public int getLanguage_ID() {
        return language_ID;
    }

    public void setLanguage_ID(int language_ID) {
        this.language_ID = language_ID;
    }

    public int getPublicher_ID() {
        return publicher_ID;
    }

    public void setPublicher_ID(int publicher_ID) {
        this.publicher_ID = publicher_ID;
    }

    public int getCategories_ID() {
        return categories_ID;
    }

    public void setCategories_ID(int categories_ID) {
        this.categories_ID = categories_ID;
    }

    public int getBranch_ID() {
        return branch_ID;
    }

    public void setBranch_ID(int branch_ID) {
        this.branch_ID = branch_ID;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Materials{" + "materielID=" + materielID + ", avaible=" + avaible + ", pageNo=" + pageNo + ", name=" + name + ", title=" + title + ", price=" + price + ", description=" + description + ", type=" + type + ", lang=" + lang + ", publicher=" + publicher + ", branch=" + branch + ", categories=" + categories + '}';
    }

    
    

}
