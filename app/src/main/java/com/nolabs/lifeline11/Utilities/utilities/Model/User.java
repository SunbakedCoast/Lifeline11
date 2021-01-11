package com.nolabs.lifeline11.Utilities.utilities.Model;

public class User {

    String firstname;
    String lastname;
    String fullname;
    String email;
    String gender;
    boolean postpermission;
    boolean verified;


    public User(){

    }

    public User(String firstname, String lastname, String fullname, String email, String gender, boolean postpermission, boolean verified) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
        this.postpermission = postpermission;
        this.verified = verified;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isPostpermission() {
        return postpermission;
    }

    public void setPostpermission(boolean postpermission) {
        this.postpermission = postpermission;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
