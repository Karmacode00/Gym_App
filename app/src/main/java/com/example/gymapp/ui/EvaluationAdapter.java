package com.example.gymapp.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gymapp.R;
import com.example.gymapp.controller.AuthController;
import com.example.gymapp.model.Evaluation;
import com.example.gymapp.model.User;

import java.util.Date;
import java.util.List;

public class EvaluationAdapter extends BaseAdapter {
    private Context ctx;
    private List<Evaluation> evaluationList;

    public EvaluationAdapter(Context ctx, List<Evaluation> evaluationList) {
        this.ctx = ctx;
        this.evaluationList = evaluationList;
    }

    @Override
    public int getCount() {
        return evaluationList.size();
    }

    @Override
    public Object getItem(int i) {
        return evaluationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return evaluationList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);

        view = inflater.inflate(R.layout.item_evaluation, null);

        AuthController authController = new AuthController(view.getContext());
        User user = authController.getUserSession();

        Evaluation evaluation = evaluationList.get(i);

        TextView tvDate = view.findViewById(R.id.item_evaluation_tv_date);
        TextView tvId = view.findViewById(R.id.item_evaluation_tv_id);
        TextView tvWeight = view.findViewById(R.id.item_evaluation_tv_weight);
        TextView tvImc = view.findViewById(R.id.item_evaluation_tv_imc);


        tvId.setText(Long.toString(evaluation.getId()));
        tvDate.setText(String.format("Fecha : %s", evaluation.getDateString()));
        tvWeight.setText(String.format("Peso : %s", evaluation.getWeightString()));
        tvImc.setText(String.format("IMC : %.2f", evaluation.calculateImc(user.getHeight())));

        return view;
    }

}
