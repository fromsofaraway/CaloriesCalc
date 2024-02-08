package com.brow.caloriescalc.dto;


public class FoodDiaryEntryDto {

    private Long userId;
    private Long productId;
    private Double amount;

    public FoodDiaryEntryDto(Long userId, Long productId, Double amount) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public FoodDiaryEntryDto(){}

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

}
