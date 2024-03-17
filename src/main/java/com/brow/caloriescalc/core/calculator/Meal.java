package com.brow.caloriescalc.core.calculator;

import com.brow.caloriescalc.model.FoodDiaryEntry;

/**
 * Represents a meal calculated from a food diary entry, providing information about consumed nutrients and calories.
 */
public class Meal {
    private FoodDiaryEntry diaryEntry;
    private Double mealFat;
    private Double mealProtein;
    private Double mealCarbs;
    private Double mealCalories;


    /**
     * Constructs a new Meal instance with the specified parameters.
     *
     * @param diaryEntry   The food diary entry representing the consumed meal.
     * @param mealFat      The amount of fat consumed in the meal.
     * @param mealProtein  The amount of protein consumed in the meal.
     * @param mealCarbs    The amount of carbohydrates consumed in the meal.
     * @param mealCalories The total calories consumed in the meal.
     */
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
