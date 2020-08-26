package com.mycompany.UrKitchen.model;

import java.util.List;

/**
 *
 * @author Leanne
 */
public class FoodType {
    private String FT;
    private List<recipeIngredient> ListRI;

    public FoodType() {
    }

    public FoodType(String FT, List<recipeIngredient> ListRI) {
        this.FT = FT;
        this.ListRI = ListRI;
    }

    

    public String getFT() {
        return FT;
    }

    public void setFT(String FT) {
        this.FT = FT;
    }

    public List<recipeIngredient> getListRI() {
        return ListRI;
    }

    public void setListRI(List<recipeIngredient> ListRI) {
        this.ListRI = ListRI;
    }
    
    
}
