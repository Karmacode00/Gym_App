package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout, btnAdd, btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogout = findViewById(R.id.activity_main_btn_logout);
        btnAdd = findViewById(R.id.activity_main_btn_add);
        btnHistory = findViewById(R.id.activity_main_btn_history);

        btnLogout.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Cerrando Sesión", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });

        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), AddActivity.class);
            startActivity(i);
            finish();
        });

        btnHistory.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), HistoryActivity.class);
            startActivity(i);
            finish();
        });
    }
}