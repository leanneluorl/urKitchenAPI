package com.mycompany.UrKitchen.model;

public class Ingredient {
    private int IngredientID;
    private String Ingredient;
    private int FoodTypeID;
    private int Amount;
    private String Unit;
    private String UnitAprox;
    private int CaloriesPerServing;
    private String Unique_Name;

    public Ingredient() {
    }

    public Ingredient(int IngredientID, String Ingredient, int FoodTypeID, int Amount, String Unit, String UnitAprox, int CaloriesPerServing, String Unique_Name) {
        this.IngredientID = IngredientID;
        this.Ingredient = Ingredient;
        this.FoodTypeID = FoodTypeID;
        this.Amount = Amount;
        this.Unit = Unit;
        this.UnitAprox = UnitAprox;
        this.CaloriesPerServing = CaloriesPerServing;
        this.Unique_Name = Unique_Name;
    }

    public int getIngredientID() {
        return IngredientID;
    }

    public void setIngredientID(int IngredientID) {
        this.IngredientID = IngredientID;
    }

    public String getIngredient() {
        return Ingredient;
    }

    public void setIngredient(String Ingredient) {
        this.Ingredient = Ingredient;
    }

    public int getFoodTypeID() {
        return FoodTypeID;
    }

    public void setFoodTypeID(int FoodTypeID) {
        this.FoodTypeID = FoodTypeID;
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

    public String getUnitAprox() {
        return UnitAprox;
    }

    public void setUnitAprox(String UnitAprox) {
        this.UnitAprox = UnitAprox;
    }

    public int getCaloriesPerServing() {
        return CaloriesPerServing;
    }

    public void setCaloriesPerServing(int CaloriesPerServing) {
        this.CaloriesPerServing = CaloriesPerServing;
    }

    public String getUnique_Name() {
        return Unique_Name;
    }

    public void setUnique_Name(String Unique_Name) {
        this.Unique_Name = Unique_Name;
    }
    
    
            
            
}
