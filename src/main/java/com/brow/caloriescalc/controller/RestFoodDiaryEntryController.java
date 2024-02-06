package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.dto.FoodDiaryEntryDto;
import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.service.FoodDiaryEntryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diary")
public class RestFoodDiaryEntryController {

    private FoodDiaryEntryService foodDiaryEntryService;

    @Autowired
    public RestFoodDiaryEntryController(FoodDiaryEntryService foodDiaryEntryService) {
        this.foodDiaryEntryService = foodDiaryEntryService;
    }

    @PostMapping
    public ResponseEntity<FoodDiaryEntry> createDiaryEntry(@Valid @RequestBody FoodDiaryEntryDto entryDto) {
        FoodDiaryEntry savedEntry = foodDiaryEntryService.createEntry(entryDto);
        return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
    }

}
