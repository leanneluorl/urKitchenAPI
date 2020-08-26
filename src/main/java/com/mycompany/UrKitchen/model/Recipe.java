package com.mycompany.UrKitchen.model;

import java.sql.Timestamp;
import java.util.List;

public class Recipe {
    private int RecipeID;
    private String RecipeTitle;
    private String RecipeInfo;
    private String MainImage;
    private Timestamp CreateDate;
    private String Author;
    private String AdminID;
    private String UserID;
    private int ViewTimes;
    private String DietTypeID;
    private String CuisineID;
    private int PerServeing_For;
    private int CookingTime;

    public Recipe() {
    }

    public Recipe(int RecipeID, String RecipeTitle, String RecipeInfo, String MainImage, Timestamp CreateDate, String Author, String AdminID, String UserID, int ViewTimes, String DietTypeID, String CuisineID, int PerServeing_For, int CookingTime) {
        this.RecipeID = RecipeID;
        this.RecipeTitle = RecipeTitle;
        this.RecipeInfo = RecipeInfo;
        this.MainImage = MainImage;
        this.CreateDate = CreateDate;
        this.Author = Author;
        this.AdminID = AdminID;
        this.UserID = UserID;
        this.ViewTimes = ViewTimes;
        this.DietTypeID = DietTypeID;
        this.CuisineID = CuisineID;
        this.PerServeing_For = PerServeing_For;
        this.CookingTime = CookingTime;
    }

    public int getRecipeID() {
        return RecipeID;
    }

    public void setRecipeID(int RecipeID) {
        this.RecipeID = RecipeID;
    }

    public String getRecipeTitle() {
        return RecipeTitle;
    }

    public void setRecipeTitle(String RecipeTitle) {
        this.RecipeTitle = RecipeTitle;
    }

    public String getRecipeInfo() {
        return RecipeInfo;
    }

    public void setRecipeInfo(String RecipeInfo) {
        this.RecipeInfo = RecipeInfo;
    }

    public String getMainImage() {
        return MainImage;
    }

    public void setMainImage(String MainImage) {
        this.MainImage = MainImage;
    }

    public Timestamp getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Timestamp CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String AdminID) {
        this.AdminID = AdminID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public int getViewTimes() {
        return ViewTimes;
    }

    public void setViewTimes(int ViewTimes) {
        this.ViewTimes = ViewTimes;
    }

    public String getDietTypeID() {
        return DietTypeID;
    }

    public void setDietTypeID(String DietTypeID) {
        this.DietTypeID = DietTypeID;
    }

    public String getCuisineID() {
        return CuisineID;
    }

    public void setCuisineID(String CuisineID) {
        this.CuisineID = CuisineID;
    }

    public int getPerServeing_For() {
        return PerServeing_For;
    }

    public void setPerServeing_For(int PerServeing_For) {
        this.PerServeing_For = PerServeing_For;
    }

    public int getCookingTime() {
        return CookingTime;
    }

    public void setCookingTime(int CookingTime) {
        this.CookingTime = CookingTime;
    }
    
    
    
    

    
    
    
    
    
    
}
