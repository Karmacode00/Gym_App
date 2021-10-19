package com.example.gymapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "users", indices = {@Index(value = "user_name", unique = true)})
public class UserEntity implements IUser{
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "height")
    private double height;

    @ColumnInfo(name = "birthday")
    private Date birthday;

    @ColumnInfo(name = "password")
    private String password;

    public UserEntity(long id, String firstName, String lastName, String userName, double height, Date birthday, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.height = height;
        this.birthday = birthday;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

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

}