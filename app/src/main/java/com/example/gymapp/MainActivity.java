package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import com.example.gymapp.model.Data;
import com.example.gymapp.ui.DataAdapter;



public class MainActivity extends AppCompatActivity {

    private Button btnLogout, btnAdd, btnHistory;
    private ListView lvData;

    private List<Data> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvData = findViewById(R.id.activity_main_lv_data);
        btnLogout = findViewById(R.id.activity_main_btn_logout);
        btnAdd = findViewById(R.id.activity_main_btn_add);
        //btnHistory = findViewById(R.id.activity_main_btn_history);

        for (int x = 0; x < 10; ++x) {
            Data newData = new Data(String.format("Date %d", x), String.format("Weight %d", x), String.format("IMC %d", x));
            newData.setId(x);
            dataList.add(newData);
        }

        DataAdapter adapter = new DataAdapter(this, dataList);

        lvData.setAdapter(adapter);


        btnLogout.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Cerrando Sesión", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });

        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), AddActivity.class);
            startActivity(i);
        });

        /*btnHistory.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), HistoryActivity.class);
            startActivity(i);
        });*/
    }
}