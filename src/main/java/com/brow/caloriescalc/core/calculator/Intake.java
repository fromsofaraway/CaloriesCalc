package com.brow.caloriescalc.core.calculator;

import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.model.User;

import java.util.List;

public class Intake {

    private User user;
    private List<FoodDiaryEntry> entries;
    private Double fat;
    private Double protein;
    private Double carbs;
    private Double calories;


    public Intake(User user, List<FoodDiaryEntry> entries, Double fat, Double protein, Double carbs, Double calories) {
        this.user = user;
        this.entries = entries;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.calories = calories;
    }

    public Intake() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<FoodDiaryEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<FoodDiaryEntry> entries) {
        this.entries = entries;
    }

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
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }
}
