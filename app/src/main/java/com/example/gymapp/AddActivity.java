package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.gymapp.controller.AuthController;
import com.example.gymapp.controller.EvaluationController;
import com.example.gymapp.model.Evaluation;
import com.example.gymapp.model.User;
import com.example.gymapp.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class AddActivity extends AppCompatActivity {

    private Button btnReturn, btnNew;
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextInputLayout tilDate, tilWeight, tilImc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnReturn = findViewById(R.id.activity_add_btn_return);
        btnNew = findViewById(R.id.activity_add_btn_new);

        tilDate = findViewById(R.id.activity_add_date);
        tilWeight = findViewById(R.id.activity_add_weight);
        tilImc = findViewById(R.id.activity_add_imc);

        tilDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate, new Date());
        });

        btnNew.setOnClickListener(view -> {
            String date = tilDate.getEditText().getText().toString();
            String weight = tilWeight.getEditText().getText().toString();
            String imc = tilImc.getEditText().getText().toString();


            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
            Date evDate = null;
            try {
                evDate = dateFormatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            double weightDouble = 0.0;

            try{
                weightDouble = Double.parseDouble(weight);
                tilWeight.setError(null);
                tilWeight.setErrorEnabled(false);
            } catch (Exception error){
                tilWeight.setError("El peso es inválido");
                return;
            }

            double imcDouble = 0.0;

            try{
                imcDouble = Double.parseDouble(imc);
                tilImc.setError(null);
                tilImc.setErrorEnabled(false);
            } catch (Exception error){
                tilImc.setError("Imc inválido");
                return;
            }

            boolean dateValid = !date.isEmpty();
            boolean weightValid = !weight.isEmpty();
            boolean imcValid = !imc.isEmpty();

            if (!dateValid || !weightValid || !imcValid) {
                tilDate.setError("Campo requerido");
                tilWeight.setError("Campo requerido");
                tilImc.setError("Campo requerido");
            } else {
                tilWeight.setError(null);
                tilWeight.setErrorEnabled(false);
            }

            if (dateValid && weightValid && imcValid) {
                AuthController authController = new AuthController(view.getContext());

                User user = authController.getUserSession();


                Evaluation evaluation = new Evaluation(evDate, weightDouble, imcDouble, user.getId());
                EvaluationController controller = new EvaluationController(view.getContext());

                controller.register(evaluation);
                Toast.makeText(view.getContext(), "Registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(view.getContext(), "No puede haber campos vacíos", Toast.LENGTH_SHORT).show();
            }

        });



        btnReturn.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }
}