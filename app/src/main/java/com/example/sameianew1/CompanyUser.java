package com.example.sameianew1;

public class CompanyUser {
    private String companyName ;

    private String phoneNumber;
    private String password ;
    private int type; // 0 Personal , 1 Company
    private String id;

    public CompanyUser() {

    }

    public CompanyUser(String companName, String phoneNumber, String password, int type, String id) {
        this.companName = companName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    public String getCompanName() {
        return companName;
    }

    public void setCompanName(String companName) {
        this.companName = companName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
