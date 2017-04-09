package com.androidtutorialshub.loginregister.model;

/**
 * Created by mahe on 4/9/2017.
 */

public class NonMember {

    private int NMID;
    private String name;
    private String email;
    private String phoneNo;


    public int getNMId() {
        return NMID;
    }

    public void setNMId(int NMID) {
        this.NMID = NMID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

}
