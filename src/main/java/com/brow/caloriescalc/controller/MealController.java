package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.core.calculator.MealCalculatorService;
import com.brow.caloriescalc.core.calculator.Meal;
import com.brow.caloriescalc.core.calculator.NutrientTotals;
import com.brow.caloriescalc.dto.MealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/meal")
public class MealController {

    private MealCalculatorService mealCalculatorService;

    @Autowired
    public MealController(MealCalculatorService mealCalculatorService) {
        this.mealCalculatorService = mealCalculatorService;
    }

    @GetMapping("/user/{userId}/meals")
    public ResponseEntity<List<MealDto>> getUserMealForSpecificDay(@PathVariable Long userId,
                                                                   @RequestParam(required = false, name = "date")
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                   LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }

        List<Meal> meals = mealCalculatorService.getUserMealsForSpecificDay(userId, date);
        List<MealDto> mealDtos = meals.stream()
                .map(meal -> mealCalculatorService.convertToDto(meal))
                .toList();
        return new ResponseEntity<>(mealDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/meals/total")
    public ResponseEntity<NutrientTotals> getNutrientTotals(@PathVariable Long userId,
                                                            @RequestParam(required = false, name = "date")
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                            LocalDate date) {

        if (date == null) {
            date = LocalDate.now();
        }

        List<Meal> meals = mealCalculatorService.getUserMealsForSpecificDay(userId, date);
        NutrientTotals totals = mealCalculatorService.calculateTotalNutrients(meals, userId, date);

        return new ResponseEntity<>(totals, HttpStatus.OK);
    }
}
