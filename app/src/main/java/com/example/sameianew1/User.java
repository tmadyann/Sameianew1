package com.example.sameianew1;

public class User {
    String name ;
    String number;
    String password ;
    String id ;

    public User(String name, String number, String password, String id) {
        this.name = name;
        this.number = number;
        this.password = password;

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", confirmation='" + confirmation + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

