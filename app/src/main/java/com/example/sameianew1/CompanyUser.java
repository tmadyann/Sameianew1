package com.example.sameianew1;

public class CompanyUser extends User {
    private String companyName ;


    public CompanyUser() {

    }

    public CompanyUser(String userName, String password, String phoneNumber, String companyName,String city,int userType) {
        super(userName, password, phoneNumber,city,userType);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "CompanyUser{" +
                "companyName='" + companyName + '\'' +
                '}';
    }
}
