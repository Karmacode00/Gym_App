package com.example.gymapp.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.gymapp.LoginActivity;
import com.example.gymapp.MainActivity;
import com.example.gymapp.dao.UserDao;
import com.example.gymapp.lib.BCrypt;
import com.example.gymapp.lib.GymAppDatabase;
import com.example.gymapp.model.User;
import com.example.gymapp.model.UserEntity;
import com.example.gymapp.model.UserMapper;

import java.util.Date;

public class AuthController {
    private final String KEY_USER_ID = "userId";
    private final String KEY_USERNAME = "userName";
    private final String KEY_FIRST_NAME = "userFirstName";
    private final String KEY_LAST_NAME = "userLastName";
    private final String KEY_USER_HEIGHT = "userHeight";

    private UserDao userDao;

    private Context ctx;
    private SharedPreferences preferences;

    public AuthController(Context ctx) {

        this.ctx = ctx;
        int PRIVATE_MODE = 0;
        String PREF_NAME = "GymAppPref";
        this.preferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.userDao = GymAppDatabase.getInstance(ctx).userDao();
    }

    private void setUserSession(User user){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putLong(KEY_USER_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getUserName());
        editor.putString(KEY_FIRST_NAME, user.getFirstName());
        editor.putString(KEY_LAST_NAME, user.getLastName());
        editor.putString(KEY_USER_HEIGHT, user.getHeightString());
        editor.apply();
    }

    public User getUserSession() {
        long id = preferences.getLong(KEY_USER_ID, 0);
        String firstName = preferences.getString(KEY_FIRST_NAME, "");
        String lastName = preferences.getString(KEY_LAST_NAME, "");
        String userName = preferences.getString(KEY_USERNAME, "");
        String heightString = preferences.getString(KEY_USER_HEIGHT, "0.0");

        User user = new User(firstName, lastName, userName, Double.parseDouble(heightString), new Date());
        user.setId(id);

        return user;
    }

    public void checkUserSession() {
        long id = preferences.getLong(KEY_USER_ID, 0);
        if (id != 0) {
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();
        }
    }

    public void register(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        UserEntity userEntity = new UserMapper(user).toEntity();

        userDao.insert(userEntity);

        Toast.makeText(ctx, String.format("Usuario %s registrado", user.getUserName()), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(i);
    }

    public void login(String userName, String password) {
        UserEntity userEntity = userDao.findByUsername(userName);

        if (userEntity == null){
            Toast.makeText(ctx, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new UserMapper(userEntity).toBase();

        if (BCrypt.checkpw(password, user.getPassword())) {
            setUserSession(user);
            Toast.makeText(ctx, String.format("Bienvenido %s", userName), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();
        } else {
            Toast.makeText(ctx, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
        }
    }

    public void logout() {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(ctx, "Cerrando Sesión", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
        ((Activity) ctx).finish();
    }

}
