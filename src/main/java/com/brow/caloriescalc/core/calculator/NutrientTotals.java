package com.brow.caloriescalc.core.calculator;

/**
 * Entity that represents total intake per day.
 */

public class NutrientTotals {

    private double totalFat;
    private double totalProtein;
    private double totalCarbs;
    private double totalCalories;

    public NutrientTotals(double totalFat, double totalProtein, double totalCarbs, double totalCalories) {
        this.totalFat = totalFat;
        this.totalProtein = totalProtein;
        this.totalCarbs = totalCarbs;
        this.totalCalories = totalCalories;
    }



    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
}
