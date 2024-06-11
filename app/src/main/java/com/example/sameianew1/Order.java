package com.example.sameianew1;

public class Order {
    private String date;
    private String payMethod;
    private int numOfKubs;
    private String personalUserName;
    private String  phoneNum;
    private String companyUserName;
    private String location;

    public Order() {
    }

    public Order(String date, String payMethod, int numOfKubs, String personalUserName, String phoneNum, String companyUserName, String location) {
        this.date = date;
        this.payMethod = payMethod;
        this.numOfKubs = numOfKubs;
        this.personalUserName = personalUserName;
        this.phoneNum = phoneNum;
        this.companyUserName = companyUserName;
        this.location = location;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public int getNumOfKubs() {
        return numOfKubs;
    }

    public void setNumOfKubs(int numOfKubs) {
        this.numOfKubs = numOfKubs;
    }

    public String getPersonalUserName() {
        return personalUserName;
    }

    public void setPersonalUserName(String personalUserName) {
        this.personalUserName = personalUserName;
    }

    public String getCompanyUserName() {
        return companyUserName;
    }

    public void setCompanyUserName(String companyUserName) {
        this.companyUserName = companyUserName;
    }
}
