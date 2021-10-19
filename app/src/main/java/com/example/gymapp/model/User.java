package com.example.gymapp.model;

import java.util.Date;

public class User implements IUser{

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private double height;
    private Date birthday;
    private String password;

    public User(String firstName, String lastName, String userName, double height, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.height = height;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public double getHeight() {
        return height;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeightString(){
        return Double.toString(height);
    }
}
