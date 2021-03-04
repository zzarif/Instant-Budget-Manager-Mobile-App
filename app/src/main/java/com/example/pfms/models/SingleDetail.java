package com.example.pfms.models;

import java.util.ArrayList;

public class SingleDetail {

    private String date;
    private float totalDebit;

    public SingleDetail(String date, float totalDebit) {
        this.date = date;
        this.totalDebit = totalDebit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(float totalDebit) {
        this.totalDebit = totalDebit;
    }
}
