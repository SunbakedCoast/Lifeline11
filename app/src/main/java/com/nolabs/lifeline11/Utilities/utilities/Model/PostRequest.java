package com.nolabs.lifeline11.Utilities.utilities.Model;

public class PostRequest {
    String hashkey;

    String title;
    String body;
    String timestamp;
    String authorname;
    String status;
    String needs;
    String notificationID;
    String location;
    String donationaddress;
    String quantity;

    public PostRequest(){

    }

    public PostRequest(String hashkey, String title, String body, String timestamp, String authorname, String status, String needs, String notificationID, String location, String donationaddress, String quantity) {
        this.hashkey = hashkey;
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
        this.authorname = authorname;
        this.status = status;
        this.needs = needs;
        this.notificationID = notificationID;
        this.location = location;
        this.donationaddress = donationaddress;
        this.quantity = quantity;
    }

    public String getHashkey() {
        return hashkey;
    }

    public void setHashkey(String hashkey) {
        this.hashkey = hashkey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNeeds() {
        return needs;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDonationaddress() {
        return donationaddress;
    }

    public void setDonationaddress(String donationaddress) {
        this.donationaddress = donationaddress;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
