package com.brow.caloriescalc.dto;


public class UserDto {

    private String username;
    private String email;
    private Integer age;
    private Integer weight;
    private Integer height;
    private String name;

    public UserDto(String username, String email, Integer age, Integer weight, Integer height, String name) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.name = name;
    }

    public UserDto(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
