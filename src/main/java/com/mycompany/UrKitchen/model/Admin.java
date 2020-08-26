package com.mycompany.UrKitchen.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Leanne
 */
public class Admin {
    private String AdminID;
    private String AdminName;
    private String AdminPWD;
    private Timestamp CreateDate;
    private String AccID;

    public Admin() {
    }

    public Admin(String AdminID, String AdminName, String AdminPWD, Timestamp CreateDate, String AccID) {
        this.AdminID = AdminID;
        this.AdminName = AdminName;
        this.AdminPWD = AdminPWD;
        this.CreateDate = CreateDate;
        this.AccID = AccID;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String AdminID) {
        this.AdminID = AdminID;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String AdminName) {
        this.AdminName = AdminName;
    }

    public String getAdminPWD() {
        return AdminPWD;
    }

    public void setAdminPWD(String AdminPWD) {
        this.AdminPWD = AdminPWD;
    }

    public Timestamp getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Timestamp CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getAccID() {
        return AccID;
    }

    public void setAccID(String AccID) {
        this.AccID = AccID;
    }
    
    
    
}
