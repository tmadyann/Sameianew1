package com.example.sameianew1;

public class PersonalUser extends User{
    private String firstName ;
    private String lastName ;
    private String id;

    public PersonalUser() {
    }

    public PersonalUser(String userName, String password, String phoneNumber, String firstName, String lastName,String id,String city,int userType) {
        super(userName, password, phoneNumber,city,userType);
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonalUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
