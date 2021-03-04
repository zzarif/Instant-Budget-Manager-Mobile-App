package com.example.pfms.models;

/**
 * This class holds categorical data
 * @author zarif
 */
public class Category {
    private String categoryTitle;
    private float expectedValue;
    private float realValue;

    public Category () {

    }

    public Category(String categoryTitle, float expectedValue, float realValue) {
        this.categoryTitle = categoryTitle;
        this.expectedValue = expectedValue;
        this.realValue = realValue;
    }


    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public float getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(float expectedValue) {
        this.expectedValue = expectedValue;
    }

    public float getRealValue() {
        return realValue;
    }

    public void setRealValue(float realValue) {
        this.realValue = realValue;
    }

    public boolean isExceeded() {
        return realValue>expectedValue;
    }

}
