package com.example.gymapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "evaluations")
public class EvaluationEntity implements IEvaluation{
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "weight")
    private double weight;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "user_id")
    private long userId;

    public EvaluationEntity(long id, double weight, Date date, long userId) {
        this.id = id;
        this.weight = weight;
        this.date = date;
        this.userId = userId;
    }

    @Override
    public long getId() {
        return id;
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
}
