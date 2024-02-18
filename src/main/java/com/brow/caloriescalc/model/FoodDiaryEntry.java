package com.brow.caloriescalc.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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
    private ZonedDateTime consumptionTime;

    public FoodDiaryEntry(User user, Product product, Double amount, ZonedDateTime consumptionTime) {
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



    public ZonedDateTime getConsumptionTime() {
        return consumptionTime;
    }

    public void setConsumptionTime(ZonedDateTime consumptionTime) {
        this.consumptionTime = consumptionTime;
    }
}

