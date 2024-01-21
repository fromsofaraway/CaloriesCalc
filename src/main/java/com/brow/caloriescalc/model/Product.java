package com.brow.caloriescalc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    @NotNull(message = "Name cannot be null")
    private String name;
    @Column
    @NotNull(message = "Fat cannot be null")
    @Min(value = 0, message = "Fat must be a positive number")
    private Double fat;
    @Column
    @NotNull(message = "Protein cannot be null")
    @Min(value = 0, message = "Protein must be a positive number")
    private Double protein;
    @Column
    @NotNull(message = "Carbs cannot be null")
    @Min(value = 0, message = "Carbs must be a positive number")
    private Double carbs;
    @Column
    private String description;

    public Product(Long id, String name, Double fat, Double protein, Double carbs, String description) {
        this.id = id;
        this.name = name;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.description = description;
    }

    public Product() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String comment) {
        this.description = comment;
    }

    @Override
    public String toString() {
        return getName();
    }
}

