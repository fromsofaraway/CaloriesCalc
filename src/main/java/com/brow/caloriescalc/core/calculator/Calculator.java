package com.brow.caloriescalc.core.calculator;

import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.model.Product;
import com.brow.caloriescalc.model.User;
import com.brow.caloriescalc.service.FoodDiaryEntryService;
import com.brow.caloriescalc.service.ProductService;
import com.brow.caloriescalc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.List;

@Component
public class Calculator {

    private final UserService userService;
    private final FoodDiaryEntryService foodDiaryEntryService;
    private final ProductService productService;

    @Autowired
    public Calculator(UserService userService, FoodDiaryEntryService foodDiaryEntryService, ProductService productService) {
        this.userService = userService;
        this.foodDiaryEntryService = foodDiaryEntryService;
        this.productService = productService;
    }

    public Intake calculate(Long userId) {
        ZonedDateTime startOfDay = userService.getStartOfDay(userId);
        ZonedDateTime endOfDay = userService.getEndOfDay(userId);
        List<FoodDiaryEntry> currentUserEntries = foodDiaryEntryService.getEntriesForCurrentBusinessDay(userId, startOfDay, endOfDay);

        return currentUserEntries.stream()
                .map(entry -> new Intake(entry.getProduct().getFat() * entry.getAmount(),
                        entry.getProduct().getProtein() * entry.getAmount(),
                        entry.getProduct().getCarbs() * entry.getAmount()
                ))
                .reduce(Intake::add)
                .orElse(new Intake(0d, 0d, 0d));
    }

    // берем юзера, берем его диари ентрис (between business day), для каждого entry высчитываем калории
    // и суммируем в объект Intake


}
