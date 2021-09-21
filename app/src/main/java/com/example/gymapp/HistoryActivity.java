package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

public class HistoryActivity extends AppCompatActivity {

    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnReturn = findViewById(R.id.activity_history_btn_return);

        btnReturn.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });
    }
}