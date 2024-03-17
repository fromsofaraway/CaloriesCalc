package com.brow.caloriescalc.service;

import com.brow.caloriescalc.core.calculator.Meal;
import com.brow.caloriescalc.core.calculator.MealCalculatorService;
import com.brow.caloriescalc.core.calculator.NutrientTotals;
import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.model.Product;
import com.brow.caloriescalc.model.Role;
import com.brow.caloriescalc.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.brow.caloriescalc.model.RoleEnum.ROLE_USER;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MealCalculatorServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private FoodDiaryEntryService foodDiaryEntryService;
    @InjectMocks
    private MealCalculatorService mealCalculatorService;

    //need to modify since intake and calc classes redesigned to nutrients total and meal
    @Test
    public void calculateTest() {
        Long userId = 1L;
        ZonedDateTime startOfDay = ZonedDateTime.now().minusHours(12);
        ZonedDateTime endOfDay = ZonedDateTime.now().plusHours(12);
        Role role = new Role(ROLE_USER);
        ZoneId zoneId = ZoneId.of("Asia/Yerevan");
        User user = new User(userId,
                "testusername",
                "brow@brow.brow",
                27,
                35,
                105,
                "George",
                "password",
                zoneId,
                role
        );

        when(userService.getStartOfDay(userId)).thenReturn(startOfDay);
        when(userService.getEndOfDay(userId)).thenReturn(endOfDay);


        Product product = new Product(1L, "Milk", 2.5d, 2.8d, 4.7d, "i <3 milk" );
        FoodDiaryEntry entry = new FoodDiaryEntry(user, product, 500d, ZonedDateTime.now());
        List<FoodDiaryEntry> entries = List.of(entry);

        when(foodDiaryEntryService.getEntriesForSpecificDate(eq(userId), any(LocalDate.class)))
                .thenReturn(entries);

        List<Meal> meals = mealCalculatorService.getUserMealsForSpecificDay(userId, LocalDate.now());
        NutrientTotals totals = mealCalculatorService.calculateTotalNutrients(meals);

        assertEquals(12.5, totals.getTotalFat(), 0.01);
        assertEquals(14d, totals.getTotalProtein(), 0.01);
        assertEquals(23.5, totals.getTotalCarbs(), 0.01);
//        assertEquals();

        verify(userService).getStartOfDay(userId);
        verify(userService).getEndOfDay(userId);
        verify(foodDiaryEntryService).getEntriesForSpecificDate(eq(userId), any(LocalDate.class));

    }
}
