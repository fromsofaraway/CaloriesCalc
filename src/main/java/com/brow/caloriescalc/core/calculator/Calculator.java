package com.brow.caloriescalc.core.calculator;

import com.brow.caloriescalc.dto.IntakeDto;
import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.service.FoodDiaryEntryService;
import com.brow.caloriescalc.service.ProductService;
import com.brow.caloriescalc.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.List;

@Component
public class Calculator {

    private final UserService userService;
    private final FoodDiaryEntryService foodDiaryEntryService;

    private final ModelMapper modelMapper;

    private Double measure = 0.01; //hardcode, to calculate everything in grams, will decide further how to implement

    @Autowired
    public Calculator(UserService userService, FoodDiaryEntryService foodDiaryEntryService, ModelMapper modelMapper) {
        this.userService = userService;
        this.foodDiaryEntryService = foodDiaryEntryService;
        this.modelMapper = modelMapper;
    }

    public Intake calculate(Long userId) {
        ZonedDateTime startOfDay = userService.getStartOfDay(userId);
        ZonedDateTime endOfDay = userService.getEndOfDay(userId);
        List<FoodDiaryEntry> currentUserEntries = foodDiaryEntryService.getEntriesForCurrentBusinessDay(userId, startOfDay, endOfDay);

        return currentUserEntries.stream()
                .map(entry -> new Intake(entry.getProduct().getFat() * entry.getAmount() * measure,
                        entry.getProduct().getProtein() * entry.getAmount() * measure,
                        entry.getProduct().getCarbs() * entry.getAmount() * measure
                ))
                .reduce(Intake::add)
                .orElse(new Intake(0d, 0d, 0d));
    }

    public IntakeDto convertToDto(Intake intake) {
        return modelMapper.map(intake, IntakeDto.class);
    }
}
