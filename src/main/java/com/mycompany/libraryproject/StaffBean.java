/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libraryproject;

import classesPackage.Members;
import classesPackage.PropertiesC;
import classesPackage.Staff;
import databasePackage.Database;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import loginPackage.Util;

/**
 *
 * @author Nemesis
 */
@Named(value = "staffBean")
@Dependent
public class StaffBean {

    /**
     * Creates a new instance of StaffBean
     */
    public StaffBean() {
    }
    private Staff staff;

    public Staff getStaff() {
        PropertiesC properties = new PropertiesC();
        Database database = new Database();

        staff = database.getStaffInfo(Util.getUserName());
        setStaff(staff);
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void fillStaff() {

    }

}
