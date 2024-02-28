package com.brow.caloriescalc.core.calculator;

import com.brow.caloriescalc.model.FoodDiaryEntry;


public class Meal {
    private FoodDiaryEntry diaryEntry;
    private Double mealFat;
    private Double mealProtein;
    private Double mealCarbs;
    private Double mealCalories;


    public Meal(FoodDiaryEntry diaryEntry, Double mealFat, Double mealProtein, Double mealCarbs, Double mealCalories) {
        this.diaryEntry = diaryEntry;
        this.mealFat = mealFat;
        this.mealProtein = mealProtein;
        this.mealCarbs = mealCarbs;
        this.mealCalories = mealCalories;
    }

    public FoodDiaryEntry getDiaryEntry() {
        return diaryEntry;
    }

    public void setDiaryEntry(FoodDiaryEntry diaryEntry) {
        this.diaryEntry = diaryEntry;
    }

    public Double getMealFat() {
        return mealFat;
    }

    public void setMealFat(Double mealFat) {
        this.mealFat = mealFat;
    }

    public Double getMealProtein() {
        return mealProtein;
    }

    public void setMealProtein(Double mealProtein) {
        this.mealProtein = mealProtein;
    }

    public Double getMealCarbs() {
        return mealCarbs;
    }

    public void setMealCarbs(Double mealCarbs) {
        this.mealCarbs = mealCarbs;
    }

    public Double getMealCalories() {
        return mealCalories;
    }

    public void setMealCalories(Double mealCalories) {
        this.mealCalories = mealCalories;
    }
}
