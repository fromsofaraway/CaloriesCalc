package com.brow.caloriescalc.service;

import com.brow.caloriescalc.core.calculator.MealCalculatorService;
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

        when(foodDiaryEntryService.getEntriesForCurrentBusinessDay(eq(userId), any(ZonedDateTime.class), any(ZonedDateTime.class)))
                .thenReturn(entries);

        Intake result = mealCalculatorService.calculateForCurrentBusinessDay(userId);

        assertEquals(12.5, result.getFat(), 0.01);
        assertEquals(14d, result.getProtein(), 0.01);
        assertEquals(23.5, result.getCarbs(), 0.01);

        verify(userService).getStartOfDay(userId);
        verify(userService).getEndOfDay(userId);
        verify(foodDiaryEntryService).getEntriesForCurrentBusinessDay(eq(userId), any(ZonedDateTime.class), any(ZonedDateTime.class));

    }
}
