package com.example.gymapp.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evaluation implements Serializable, IEvaluation {
    private long id;
    private Date date;
    private double weight;
    private long userId;

    public Evaluation(Date date, double weight, long userId) {
        this.date = date;
        this.weight = weight;
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
    public long getUserId() {
        return userId;
    }

    public String getWeightString(){
        return Double.toString(weight);
    }

    public String getDateString(){
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

}
