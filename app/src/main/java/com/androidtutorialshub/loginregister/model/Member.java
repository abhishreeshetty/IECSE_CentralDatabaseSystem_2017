package com.androidtutorialshub.loginregister.model;

/**
 * Created by mahe on 4/9/2017.
 */

public class Member {

    private int memID;
    private String name;
    private String email;
    private String accessLevel;
    private String phoneNo;
    private String dept;



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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }



    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

}
