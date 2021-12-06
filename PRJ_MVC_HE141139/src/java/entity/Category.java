/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author I'mTrung
 */
public class Category {
    private int cateID;
    private String catName;
    private int status;

    public Category() {
    }

    public Category(int cateID, String catName, int status) {
        this.cateID = cateID;
        this.catName = catName;
        this.status = status;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Category(String catName,int status) {
        this.catName = catName;
        this.status = status;
    }
    
}
