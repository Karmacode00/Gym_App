package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.gymapp.controller.AuthController;
import com.example.gymapp.controller.EvaluationController;
import com.example.gymapp.model.Evaluation;
import com.example.gymapp.model.User;
import com.example.gymapp.ui.EvaluationAdapter;
import com.example.gymapp.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextView tvTitle,tvClearFilter;
    private Button btnLogout, btnAdd, btnFilter;
    private ListView lvEvaluation;
    private TextInputLayout tilFrom, tilTo;
    private AuthController authController;
    private EvaluationController evaluationController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authController = new AuthController(this);
        evaluationController = new EvaluationController(this);

        tvTitle = findViewById(R.id.activity_main_tv_title);
        tvClearFilter = findViewById(R.id.activity_main_tv_clear);
        tilFrom = findViewById(R.id.activity_main_from);
        tilTo = findViewById(R.id.activity_main_to);

        lvEvaluation = findViewById(R.id.activity_main_lv_evaluation);
        btnLogout = findViewById(R.id.activity_main_btn_logout);
        btnAdd = findViewById(R.id.activity_main_btn_add);
        btnFilter = findViewById(R.id.activity_main_btn_filter);

        User user = authController.getUserSession();

        tvTitle.setText(String.format("Evaluaciones de %s", user.getUserName()));

        tilFrom.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilFrom, new Date());
        });

        tilTo.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilTo, new Date());
        });


        List<Evaluation> evaluationList = evaluationController.getAll();

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

        btnFilter.setOnClickListener(view -> {
            String fromStr = tilFrom.getEditText().getText().toString();
            String toStr = tilTo.getEditText().getText().toString();

            boolean validFrom = !fromStr.isEmpty();
            boolean validTo = !toStr.isEmpty();

            if (validFrom && validTo) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);

                try {
                    Date from = dateFormatter.parse(fromStr);
                    Date to = dateFormatter.parse(toStr);

                    List<Evaluation> evaluationRangeList = evaluationController.getRange(from, to);
                    EvaluationAdapter rangeAdapter = new EvaluationAdapter(this, evaluationRangeList);

                    lvEvaluation.setAdapter(rangeAdapter);

                    lvEvaluation.setOnItemClickListener(((adapterView, rangeView, index, id) -> {
                        Evaluation evaluation = evaluationRangeList.get(index);

                        Intent i = new Intent(rangeView.getContext(), DetailActivity.class);
                        i.putExtra("evaluation", evaluation);
                        rangeView.getContext().startActivity(i);
                    }));


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        tvClearFilter.setOnClickListener(view -> {
            tilFrom.getEditText().setText("");
            tilTo.getEditText().setText("");
            lvEvaluation.setAdapter(adapter);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Evaluation> evaluationList = evaluationController.getAll();
        EvaluationAdapter adapter = new EvaluationAdapter(this, evaluationList);

        lvEvaluation.setAdapter(adapter);

        lvEvaluation.setOnItemClickListener(((adapterView, view, index, id) -> {
            Evaluation evaluation = evaluationList.get(index);

            Intent i = new Intent(view.getContext(), DetailActivity.class);
            i.putExtra("evaluation", evaluation);
            view.getContext().startActivity(i);
        }));
    }
}