package com.example.gymapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gymapp.model.UserEntity;

@Dao
public interface UserDao {
    @Query("SELECT id, first_name, last_name, user_name, birthday, height, password FROM users WHERE user_name = :username LIMIT 1")
    UserEntity findByUsername (String username);

    @Insert
    long insert(UserEntity user);
}
