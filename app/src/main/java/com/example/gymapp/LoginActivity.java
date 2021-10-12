package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.gymapp.controller.AuthController;
import com.example.gymapp.model.User;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private TextInputLayout tilUser, tilPassword;
    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authController = new AuthController(this);

        authController.checkUserSession();


        btnLogin = findViewById(R.id.activity_login_btn_login);
        btnRegister = findViewById(R.id.activity_login_btn_register);
        tilUser = findViewById(R.id.activity_login_field_user);
        tilPassword = findViewById(R.id.activity_login_field_password);


        btnLogin.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Iniciando sesión", Toast.LENGTH_SHORT).show();

            String userName = tilUser.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            boolean userValid = !userName.isEmpty();
            boolean passwordValid = !password.isEmpty();

            if (!userValid) {
                tilUser.setError("El usuario es inválido");
            } else {
                tilUser.setError(null);
                tilUser.setErrorEnabled(false);
            }

            if (!passwordValid) {
                tilPassword.setError("Campo requerido");
            } else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            if (userValid && passwordValid) {
                authController.login(userName, password);
            } else {
                Toast.makeText(view.getContext(), "Campos inválidos", Toast.LENGTH_SHORT).show();
            }

            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });

        btnRegister.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), RegisterActivity.class);
            startActivity(i);
            finish();
        });
    }
}