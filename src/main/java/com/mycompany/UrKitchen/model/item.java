package com.mycompany.UrKitchen.model;

/**
 *
 * @author Leanne
 */
public class item {
    private int itemID;
    private String item;
    private String Information;
    private String Uniq_name;

    public item() {
    }

    public item(int itemID, String item, String Information, String Uniq_name) {
        this.itemID = itemID;
        this.item = item;
        this.Information = Information;
        this.Uniq_name = Uniq_name;
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

   
}
