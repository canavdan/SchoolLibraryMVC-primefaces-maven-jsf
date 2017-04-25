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
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Nemesis
 */
@Named(value = "libraryCollectionBean")
@SessionScoped
public class libraryCollectionBean implements Serializable {

    /**
     * Creates a new instance of libraryCollectionBean
     */
    public libraryCollectionBean() {
    }
    private PieChartModel typeModel;
    private PieChartModel langModel;
    private PieChartModel catModel;
    private PieChartModel branchModel;

    public PieChartModel getTypeModel() {
        return typeModel;
    }

    public void setTypeModel(PieChartModel typeModel) {
        this.typeModel = typeModel;
    }

    public PieChartModel getLangModel() {
        return langModel;
    }

    public void setLangModel(PieChartModel langModel) {
        this.langModel = langModel;
    }

    public PieChartModel getCatModel() {
        return catModel;
    }

    public void setCatModel(PieChartModel catModel) {
        this.catModel = catModel;
    }

    public PieChartModel getBranchModel() {
        return branchModel;
    }

    public void setBranchModel(PieChartModel branchModel) {
        this.branchModel = branchModel;
    }

    @PostConstruct
    public void init() {
        createPieModels();
    }

    public void createPieModels() {
        Database database = new Database();
        typeModel = database.typeModel();
        langModel = database.langModel();
        catModel = database.catModel();
        branchModel = database.branchModel();
        
        /*branchModel = new PieChartModel();
         
        branchModel.set("Brand 1", 540);
        branchModel.set("Brand 2", 325);
        branchModel.set("Brand 3", 702);
        branchModel.set("Brand 4", 421);
         
        branchModel.setTitle("Simple Pie");
        branchModel.setLegendPosition("w");*/
          
    }
}
