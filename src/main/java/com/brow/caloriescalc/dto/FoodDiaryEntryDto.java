package com.brow.caloriescalc.dto;

import java.time.LocalDateTime;

public class FoodDiaryEntryDto {

    private Long userId;
    private Long productId;
    private Double amount;
    private LocalDateTime consumptionTime;

    public FoodDiaryEntryDto(Long userId, Long productId, Double amount, LocalDateTime consumptionTime) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
        this.consumptionTime = consumptionTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
