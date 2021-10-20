package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.gymapp.controller.AuthController;
import com.example.gymapp.model.User;
import com.example.gymapp.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private Button btnReturn, btnRegister;
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextInputLayout tilBirthday, tilFirstName, tilLastName, tilUser, tilPassword, tilHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tilFirstName = findViewById(R.id.activity_register_field_first_name);
        tilLastName = findViewById(R.id.activity_register_field_last_name);
        tilUser = findViewById(R.id.activity_register_field_user);
        tilHeight = findViewById(R.id.activity_register_field_height);
        tilPassword = findViewById(R.id.activity_register_field_password);
        tilBirthday = findViewById(R.id.activity_register_field_birthday);
        btnRegister = findViewById(R.id.activity_register_btn_register);
        btnReturn = findViewById(R.id.activity_register_btn_return);

        tilBirthday.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilBirthday, new Date());
        });

        btnRegister.setOnClickListener(view -> {
            String firstName = tilFirstName.getEditText().getText().toString();
            String lastName = tilLastName.getEditText().getText().toString();
            String userName = tilUser.getEditText().getText().toString();
            String height = tilHeight.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();
            String birthday = tilBirthday.getEditText().getText().toString();

            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
            Date birthdayDate = null;
            try {
                birthdayDate = dateFormatter.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            double heightDouble = 0.0;

            try{
               heightDouble = Double.parseDouble(height);
               tilHeight.setError(null);
               tilHeight.setErrorEnabled(false);
            } catch (Exception error){
               tilHeight.setError("La estatura es inválida");
               return;
            }

            boolean userNameValid = !userName.isEmpty();
            boolean passwordValid = !password.isEmpty();
            boolean firstNameValid = !firstName.isEmpty();
            boolean lastNameValid = !lastName.isEmpty();
            boolean heightValid = !height.isEmpty();
            boolean birthdayValid = !birthday.isEmpty();

            if (!userNameValid || !passwordValid || !firstNameValid || !lastNameValid || !heightValid || !birthdayValid) {
                tilPassword.setError("Campo requerido");
            } else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            if (userNameValid && passwordValid && firstNameValid && lastNameValid && heightValid && birthdayValid) {
                User user = new User(firstName, lastName, userName, heightDouble, birthdayDate);
                user.setPassword(password);

                AuthController controller = new AuthController(view.getContext());

                controller.register(user);
            } else {
                Toast.makeText(view.getContext(), "No puede haber campos vacíos", Toast.LENGTH_SHORT).show();
            }
        });

        btnReturn.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }
}