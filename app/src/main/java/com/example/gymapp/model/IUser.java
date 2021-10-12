package com.example.gymapp.model;

import java.util.Date;

public interface IUser {
    long getId();

    String getFirstName();

    String getLastName();

    String getUserName();

    String getHeight();

    Date getBirthday();

    String getPassword();
}
