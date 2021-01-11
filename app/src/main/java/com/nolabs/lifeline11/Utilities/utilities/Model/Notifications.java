package com.nolabs.lifeline11.Utilities.utilities.Model;

public class Notifications {
   String title;
   String timestamp;

    public Notifications(){

    }

    public Notifications(String title, String timestamp) {
        this.title = title;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
