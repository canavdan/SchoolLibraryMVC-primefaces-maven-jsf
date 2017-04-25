/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesPackage;

import java.sql.Date;

/**
 *
 * @author Nemesis
 */
public class Loan {
    private int loanID,extend;
    private Date out,due;
    private String materielName;

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

    @Override
    public String toString() {
        return "Loan{" + "loanID=" + loanID + ", extend=" + extend + ", out=" + out + ", due=" + due + ", materielName=" + materielName + '}';
    }
    
    
    
}
