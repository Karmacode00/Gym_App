package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymapp.model.Evaluation;

public class DetailActivity extends AppCompatActivity {
    private Button btnMain;
    private TextView tvId;
    private TextView tvDate;
    private TextView tvWeight;
    private TextView tvImc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Evaluation evaluation = (Evaluation) getIntent().getSerializableExtra("evaluation");


        tvId = findViewById(R.id.detail_tv_id);
        tvDate = findViewById(R.id.detail_tv_date);
        tvWeight = findViewById(R.id.detail_tv_weight);
        tvImc = findViewById(R.id.detail_tv_imc);
        btnMain = findViewById(R.id.activity_detail_back_button);

        tvId.setText(Long.toString(evaluation.getId()));
        tvDate.setText(evaluation.getDate());
        tvWeight.setText(evaluation.getWeight());
        tvImc.setText(evaluation.getImc());

        btnMain.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });
    }
}