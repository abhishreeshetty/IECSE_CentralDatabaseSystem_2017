package com.androidtutorialshub.loginregister.model;

/**
 * Created by lalit on 9/12/2016.
 */
public class User {

    private int memID;
    private String name;
    private String email;
    private String password;
    private long regNo;
    private long phoneNo;


    public int getMemId() {
        return memID;
    }

    public void setMemId(int memID) {
        this.memID = memID;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }
    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }
}
