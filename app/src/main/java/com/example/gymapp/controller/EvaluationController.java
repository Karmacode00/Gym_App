package com.example.gymapp.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.example.gymapp.MainActivity;
import com.example.gymapp.dao.EvaluationDao;
import com.example.gymapp.lib.GymAppDatabase;
import com.example.gymapp.model.Evaluation;
import com.example.gymapp.model.EvaluationEntity;
import com.example.gymapp.model.EvaluationMapper;
import com.example.gymapp.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluationController {
    private Context ctx;
    private EvaluationDao evaluationDao;

    public EvaluationController(Context ctx) {
        this.ctx = ctx;
        this.evaluationDao = GymAppDatabase.getInstance(ctx).evaluationDao();
    }


    public void register(Evaluation evaluation) {
        EvaluationMapper mapper = new EvaluationMapper(evaluation);
        EvaluationEntity newEvaluation = mapper.toEntity();
        evaluationDao.insert(newEvaluation);
        ((Activity) ctx).onBackPressed();
      /*  Intent i = new Intent(ctx, MainActivity.class);
        ctx.startActivity(i);
        ((Activity) ctx).finish();*/
    }

    public void delete(long id) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        evaluationDao.delete(id);
                        ((Activity) ctx).onBackPressed();
                    } catch(Error error) {
                        error.printStackTrace();
                        Toast.makeText(this.ctx, "Error al eliminar la evaluación.", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this.ctx);
        builder.setMessage("¿Seguro que quieres eliminar esta evaluación?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    public List<Evaluation> getAll() {
        AuthController authController = new AuthController(ctx);
        User user = authController.getUserSession();

        List<EvaluationEntity> evaluationEntityList = evaluationDao.findAll(user.getId());

        List<Evaluation> evaluationList = new ArrayList<>();

        for(EvaluationEntity evaluationEntity : evaluationEntityList){
            EvaluationMapper mapper = new EvaluationMapper(evaluationEntity);
            Evaluation evaluation = mapper.toBase();
            evaluationList.add(evaluation);
        }
        return evaluationList;
    }

    public List<Evaluation> getRange(Date from, Date to) {
        AuthController authController = new AuthController(ctx);
        User user = authController.getUserSession();
        List<EvaluationEntity> evaluationEntityList = evaluationDao.findByRange(from, to, user.getId());

        List<Evaluation> evaluationList = new ArrayList<>();

        for(EvaluationEntity evaluationEntity : evaluationEntityList){
            EvaluationMapper mapper = new EvaluationMapper(evaluationEntity);
            Evaluation evaluation = mapper.toBase();
            evaluationList.add(evaluation);
        }
        return evaluationList;
    }

}

