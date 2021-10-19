package com.example.gymapp.model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evaluation implements Serializable, IEvaluation {
    private long id;
    private Date date;
    private double weight;
    private double imc;
    private long userId;

    public Evaluation(Date date, double weight, double imc, long userId) {
        this.date = date;
        this.weight = weight;
        this.imc = imc;
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

    @Override
    public double getImc() {
        return imc;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    public String getWeightString(){
        return Double.toString(weight);
    }

    public String getImcString(){
        return Double.toString(imc);
    }

    public String getDateString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);

    }
}
