package com.brow.caloriescalc.dto;

public class CommonResponseDto {

    private String message;

    public CommonResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
