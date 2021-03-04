package com.example.pfms.models;

public class User {

    private int id;
    private String username;
    private String password;
    private float initCapital;
    private float budget;
    private String email;
    private String startDate;
    private int loggedIn;

    public int getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(int loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public User() {
        id=0;
        username=password=null;
        initCapital=budget =0.0F;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", initCapital=" + initCapital +
                ", budget=" + budget +
                ", email='" + email + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getInitCapital() {
        return initCapital;
    }

    public void setInitCapital(float initCapital) {
        this.initCapital = initCapital;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }
}
