package com.nolabs.lifeline11.Utilities.utilities.Model;

public class Donation {
    String userID;
    String name;
    String donated;
    String date;

    public Donation(){

    }

    public Donation(String userID, String name, String donated, String date) {
        this.userID = userID;
        this.name = name;
        this.donated = donated;
        this.date = date;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDonated() {
        return donated;
    }

    public void setDonated(String donated) {
        this.donated = donated;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
