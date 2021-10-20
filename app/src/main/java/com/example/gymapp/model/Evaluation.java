package com.example.gymapp.model;


import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evaluation implements Serializable, IEvaluation {
    private long id;
    private Date date;
    private double weight;
    //private double imc;
    private long userId;

    public Evaluation(Date date, double weight, long userId) {
        this.date = date;
        this.weight = weight;
        //this.imc = imc;
        this.userId = userId;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public Date getDate() {
        return date;
    }

    /*@Override
    public double getImc() {
        return imc;
    }*/

    @Override
    public long getUserId() {
        return userId;
    }

    public String getWeightString(){
        return Double.toString(weight);
    }

    /*public String getImcString(){
        return Double.toString(imc);
    }*/

    public String getDateString(){
        //Log.d("date prueba", String.valueOf(date));
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public double calculateImc(double height) {
        return (weight / ((height/100) * (height/100)));
    }

    public String calculateImcString(double height) {
        return Double.toString(calculateImc(height));
    }

}
