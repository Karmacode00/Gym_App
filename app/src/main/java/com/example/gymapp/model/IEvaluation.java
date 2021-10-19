package com.example.gymapp.model;

import java.util.Date;

public interface IEvaluation {
    long getId();
    double getWeight();
    Date getDate();
    double getImc();
    long getUserId();
}
