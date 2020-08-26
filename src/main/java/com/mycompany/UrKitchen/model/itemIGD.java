package com.mycompany.UrKitchen.model;

/**
 *
 * @author Leanne
 */
public class itemIGD {
    private int itemID;
    private String item;
    private String Information;
    private String Uniq_name;
    private String foodTypeID;
    private String FTimage;

    public itemIGD() {
    }

    public itemIGD(int itemID, String item, String Information, String Uniq_name, String foodTypeID, String FTimage) {
        this.itemID = itemID;
        this.item = item;
        this.Information = Information;
        this.Uniq_name = Uniq_name;
        this.foodTypeID = foodTypeID;
        this.FTimage = FTimage;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String Information) {
        this.Information = Information;
    }

    public String getUniq_name() {
        return Uniq_name;
    }

    public void setUniq_name(String Uniq_name) {
        this.Uniq_name = Uniq_name;
    }

    public String getFoodTypeID() {
        return foodTypeID;
    }

    public void setFoodTypeID(String foodTypeID) {
        this.foodTypeID = foodTypeID;
    }

    public String getFTimage() {
        return FTimage;
    }

    public void setFTimage(String FTimage) {
        this.FTimage = FTimage;
    }
    
    
    
    
   
}
