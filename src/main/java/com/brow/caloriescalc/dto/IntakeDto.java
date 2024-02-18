package com.brow.caloriescalc.dto;

public class IntakeDto {

    private Long userId;
    private Double fat;
    private Double protein;
    private Double carbs;

    public IntakeDto(Long userId, Double fat, Double protein, Double carbs) {
        this.userId = userId;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
    }

    public IntakeDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
