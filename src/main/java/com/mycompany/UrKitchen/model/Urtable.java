/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.UrKitchen.model;

import java.sql.Date;

/**
 *
 * @author Leanne
 */
public class Urtable {
    private String UrtableID;
    private String UserID;
    private String today;
    private String RecipeID;
    private String RecipeTitle;
    private String MainImage;
    
    public Urtable() {
    }

    public Urtable(String UrtableID, String UserID, String today, String RecipeID, String RecipeTitle, String MainImage) {
        this.UrtableID = UrtableID;
        this.UserID = UserID;
        this.today = today;
        this.RecipeID = RecipeID;
        this.RecipeTitle = RecipeTitle;
        this.MainImage = MainImage;
    }

    public String getUrtableID() {
        return UrtableID;
    }

    public void setUrtableID(String UrtableID) {
        this.UrtableID = UrtableID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getRecipeID() {
        return RecipeID;
    }

    public void setRecipeID(String RecipeID) {
        this.RecipeID = RecipeID;
    }

    public String getRecipeTitle() {
        return RecipeTitle;
    }

    public void setRecipeTitle(String RecipeTitle) {
        this.RecipeTitle = RecipeTitle;
    }

    public String getMainImage() {
        return MainImage;
    }

    public void setMainImage(String MainImage) {
        this.MainImage = MainImage;
    }

    
    
    
    
    
}
