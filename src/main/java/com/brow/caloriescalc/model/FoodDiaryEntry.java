package com.brow.caloriescalc.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_diary_entries")
public class FoodDiaryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime consumptionTime;

    public FoodDiaryEntry(User user, Product product, Double amount, LocalDateTime consumptionTime) {
        this.user = user;
        this.product = product;
        this.amount = amount;
        this.consumptionTime = consumptionTime;
    }

    public FoodDiaryEntry() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getConsumptionTime() {
        return consumptionTime;
    }

    public void setConsumptionTime(LocalDateTime consumptionTime) {
        this.consumptionTime = consumptionTime;
    }
}

