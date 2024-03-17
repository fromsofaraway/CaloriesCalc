package com.brow.caloriescalc.core.calculator;

import com.brow.caloriescalc.dto.MealDto;
import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.service.FoodDiaryEntryService;
import com.brow.caloriescalc.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealCalculatorService {

    private final UserService userService;
    private final FoodDiaryEntryService foodDiaryEntryService;
    private final ModelMapper modelMapper;

    private Double measure = 0.01; //hardcode, to calculate everything in grams, will decide further how to implement

    @Autowired
    public MealCalculatorService(UserService userService, FoodDiaryEntryService foodDiaryEntryService, ModelMapper modelMapper) {
        this.userService = userService;
        this.foodDiaryEntryService = foodDiaryEntryService;
        this.modelMapper = modelMapper;
    }

    public MealDto convertToDto(Meal meal) {
        return modelMapper.map(meal, MealDto.class);
    }



    private Meal calculateDiaryIntoMeal(FoodDiaryEntry entry) {
        double mealFat = entry.getProduct().getFat() * entry.getAmount() * measure;
        double mealProtein = entry.getProduct().getProtein() * entry.getAmount() * measure;
        double mealCarbs = entry.getProduct().getCarbs() * entry.getAmount() * measure;
        double mealCalories = mealFat * 9 + mealProtein * 4 + mealCarbs * 4;
        return new Meal(entry, mealFat, mealProtein, mealCarbs, mealCalories);
    }

    public List<Meal> getUserMealsForSpecificDay(Long userId, LocalDate date) {
        return foodDiaryEntryService.getEntriesForSpecificDate(userId, date)
                .stream()
                .map(this::calculateDiaryIntoMeal)
                .collect(Collectors.toList());
    }

    public NutrientTotals calculateTotalNutrients(List<Meal> meals) {
        return new NutrientTotals(
                meals.stream().mapToDouble(Meal::getMealFat).sum(),
                meals.stream().mapToDouble(Meal::getMealProtein).sum(),
                meals.stream().mapToDouble(Meal::getMealCarbs).sum(),
                meals.stream().mapToDouble(Meal::getMealCalories).sum()
        );
    }
}
