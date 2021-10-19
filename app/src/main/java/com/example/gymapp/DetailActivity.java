package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymapp.controller.EvaluationController;
import com.example.gymapp.model.Evaluation;

public class DetailActivity extends AppCompatActivity {
    private Button btnMain, btnDelete;
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
        btnDelete = findViewById(R.id.activity_detail_delete_button);

        tvId.setText(Long.toString(evaluation.getId()));
        tvDate.setText(String.format("Fecha : %s", evaluation.getDateString()));
        tvWeight.setText(String.format("Peso : %s", evaluation.getWeightString()));
        tvImc.setText(String.format("IMC : %s", evaluation.getImcString()));


        btnDelete.setOnClickListener(view -> {
            EvaluationController controller = new EvaluationController(view.getContext());
            controller.delete(evaluation.getId());
        });

        btnMain.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }
}