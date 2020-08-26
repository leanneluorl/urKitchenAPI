package com.mycompany.UrKitchen.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Leanne
 */
public class User {
    private String Email;
    private String UserPWD;
    private String FirstName;
    private String LastName;
    private Date DateOfBirth;
    private Timestamp RegisterDate;
    private String MobileNo;
    private String AccID;
    private String UserID;

    public User() {
    }

    public User(String Email, String UserPWD, String FirstName, String LastName, Date DateOfBirth, Timestamp RegisterDate, String MobileNo, String AccID, String UserID) {
        this.Email = Email;
        this.UserPWD = UserPWD;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.DateOfBirth = DateOfBirth;
        this.RegisterDate = RegisterDate;
        this.MobileNo = MobileNo;
        this.AccID = AccID;
        this.UserID = UserID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUserPWD() {
        return UserPWD;
    }

    public void setUserPWD(String UserPWD) {
        this.UserPWD = UserPWD;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public Timestamp getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(Timestamp RegisterDate) {
        this.RegisterDate = RegisterDate;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    public String getAccID() {
        return AccID;
    }

    public void setAccID(String AccID) {
        this.AccID = AccID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    
  
    
}
