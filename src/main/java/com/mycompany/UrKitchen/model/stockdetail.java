/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.UrKitchen.model;

/**
 *
 * @author Leanne
 */
public class stockdetail {
    private String SDID;
    private String IngredientID;
    private int Amount;
    private String foodtype;
    
    private String LocationID;
    private String IGDImage;
    private String LocationImage;
    private String foodtypeID;

    public stockdetail() {
    }

    public stockdetail(String SDID, String IngredientID, int Amount, String foodtype, String LocationID, String IGDImage, String LocationImage, String foodtypeID) {
        this.SDID = SDID;
        this.IngredientID = IngredientID;
        this.Amount = Amount;
        this.foodtype = foodtype;
        this.LocationID = LocationID;
        this.IGDImage = IGDImage;
        this.LocationImage = LocationImage;
        this.foodtypeID = foodtypeID;
    }

    public String getSDID() {
        return SDID;
    }

    public void setSDID(String SDID) {
        this.SDID = SDID;
    }

    public String getIngredientID() {
        return IngredientID;
    }

    public void setIngredientID(String IngredientID) {
        this.IngredientID = IngredientID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getLocationID() {
        return LocationID;
    }

    public void setLocationID(String LocationID) {
        this.LocationID = LocationID;
    }

    public String getIGDImage() {
        return IGDImage;
    }

    public void setIGDImage(String IGDImage) {
        this.IGDImage = IGDImage;
    }

    public String getLocationImage() {
        return LocationImage;
    }

    public void setLocationImage(String LocationImage) {
        this.LocationImage = LocationImage;
    }

    public String getFoodtypeID() {
        return foodtypeID;
    }

    public void setFoodtypeID(String foodtypeID) {
        this.foodtypeID = foodtypeID;
    }

    
    
    
    
}
