package com.brow.caloriescalc.dto;

public class MealDto {
    private Double mealFat;
    private Double mealProtein;
    private Double mealCarbs;
    private Double mealCalories;
    private FoodDiaryEntryDto diaryEntry;

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

    public FoodDiaryEntryDto getDiaryEntry() {
        return diaryEntry;
    }

    public void setDiaryEntry(FoodDiaryEntryDto diaryEntry) {
        this.diaryEntry = diaryEntry;
    }
}
