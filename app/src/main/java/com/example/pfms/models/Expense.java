package com.example.pfms.models;

/**
 * This class holds user expense data
 * @author zarif
 */
public class Expense {

    String category;
    float amount;
    String date;

    public Expense () {
        category=date=null;
        amount=0.0F;
    }

    public Expense(String name, float amount, String date) {
        this.category = name;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
