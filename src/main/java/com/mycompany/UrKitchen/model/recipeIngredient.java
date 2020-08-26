package com.mycompany.UrKitchen.model;

/**
 *
 * @author Leanne
 */
public class recipeIngredient {
    private String RecipeIngredientID;
    private String IngredientID;
    private String FoodType;
    private int Amount;
    private String Unit;
    private String FoodTypeID;

    public recipeIngredient() {
    }

    public recipeIngredient(String RecipeIngredientID, String IngredientID, String FoodType, int Amount, String Unit, String FoodTypeID) {
        this.RecipeIngredientID = RecipeIngredientID;
        this.IngredientID = IngredientID;
        this.FoodType = FoodType;
        this.Amount = Amount;
        this.Unit = Unit;
        this.FoodTypeID = FoodTypeID;
    }

    public String getRecipeIngredientID() {
        return RecipeIngredientID;
    }

    public void setRecipeIngredientID(String RecipeIngredientID) {
        this.RecipeIngredientID = RecipeIngredientID;
    }

    public String getIngredientID() {
        return IngredientID;
    }

    public void setIngredientID(String IngredientID) {
        this.IngredientID = IngredientID;
    }

    public String getFoodType() {
        return FoodType;
    }

    public void setFoodType(String FoodType) {
        this.FoodType = FoodType;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public String getFoodTypeID() {
        return FoodTypeID;
    }

    public void setFoodTypeID(String FoodTypeID) {
        this.FoodTypeID = FoodTypeID;
    }

    
    
    
}
