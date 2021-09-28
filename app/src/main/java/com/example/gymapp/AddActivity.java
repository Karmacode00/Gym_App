package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

import com.example.gymapp.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;



public class AddActivity extends AppCompatActivity {

    private Button btnReturn;
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextInputLayout addDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnReturn = findViewById(R.id.activity_add_btn_return);
        addDate = findViewById(R.id.activity_add_date);

        addDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, addDate, new Date());
        });

        btnReturn.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
        });
    }
}