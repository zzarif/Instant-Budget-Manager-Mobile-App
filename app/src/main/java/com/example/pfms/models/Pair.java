package com.example.pfms.models;

/**
 * This class holds key value pairs
 * @author zarif
 */
public class Pair {

    private String key;
    private float value;

    public Pair() {
    }

    public Pair(String key, float value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
