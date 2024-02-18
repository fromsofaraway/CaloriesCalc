package com.brow.caloriescalc.core.calculator;

import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.model.User;

import java.util.List;

public class Intake {

    private User user;
    //    private List<FoodDiaryEntry> entries;
    private Double fat;
    private Double protein;
    private Double carbs;
    private Double calories;


    public Intake(Double fat, Double protein, Double carbs) {
//        this.user = user;
//        this.entries = entries;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
    }

    public Intake() {
    }

    public Intake add(Intake other) {
        return new Intake(this.protein + other.protein, this.fat + other.fat, this.carbs + other.carbs);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public List<FoodDiaryEntry> getEntries() {
//        return entries;
//    }
//
//    public void setEntries(List<FoodDiaryEntry> entries) {
//        this.entries = entries;
//    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getCalories() {
        return this.fat * 9 + this.protein * 4 + this.carbs * 4;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }
}
