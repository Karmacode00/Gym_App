package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.gymapp.controller.AuthController;
import com.example.gymapp.model.Evaluation;
import com.example.gymapp.model.User;
import com.example.gymapp.ui.EvaluationAdapter;
import com.example.gymapp.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {
    private TextView tvTitle;
    private Button btnLogout, btnAdd;
    private ListView lvEvaluation;
    private TextInputLayout tilFrom, tilTo;
    private AuthController authController;

    private List<Evaluation> evaluationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authController = new AuthController(this);

        tvTitle = findViewById(R.id.activity_main_tv_title);
        tilFrom = findViewById(R.id.activity_main_from);
        tilTo = findViewById(R.id.activity_main_to);

        lvEvaluation = findViewById(R.id.activity_main_lv_evaluation);
        btnLogout = findViewById(R.id.activity_main_btn_logout);
        btnAdd = findViewById(R.id.activity_main_btn_add);

        User user = authController.getUserSession();

        tvTitle.setText(String.format("Evaluaciones de %s", user.getUserName()));

        tilFrom.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilFrom, new Date());
        });

        tilTo.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilTo, new Date());
        });

        for (int x = 0; x < 10; ++x) {
            Evaluation newEvaluation = new Evaluation(String.format("Date %d", x), String.format("Weight %d", x), String.format("IMC %d", x));
            newEvaluation.setId(x);
            evaluationList.add(newEvaluation);
        }

        EvaluationAdapter adapter = new EvaluationAdapter(this, evaluationList);

        lvEvaluation.setAdapter(adapter);

        lvEvaluation.setOnItemClickListener(((adapterView, view, index, id) -> {
            Evaluation evaluation = evaluationList.get(index);

            Intent i = new Intent(view.getContext(), DetailActivity.class);
            i.putExtra("evaluation", evaluation);
            view.getContext().startActivity(i);
        }));


        btnLogout.setOnClickListener(view -> {
            authController.logout();
        });

        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), AddActivity.class);
            startActivity(i);
        });

    }
}