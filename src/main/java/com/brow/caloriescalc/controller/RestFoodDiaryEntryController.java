package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.dto.FoodDiaryEntryDto;
import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.service.FoodDiaryEntryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/diary")
public class RestFoodDiaryEntryController {

    private final FoodDiaryEntryService foodDiaryEntryService;

    @Autowired
    public RestFoodDiaryEntryController(FoodDiaryEntryService foodDiaryEntryService) {
        this.foodDiaryEntryService = foodDiaryEntryService;
    }

    @PostMapping
    public ResponseEntity<FoodDiaryEntry> createDiaryEntry(@Valid @RequestBody FoodDiaryEntryDto entryDto) {
        FoodDiaryEntry savedEntry = foodDiaryEntryService.createEntry(entryDto);
        return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FoodDiaryEntryDto>> getAllEntries() {
        return new ResponseEntity<>(foodDiaryEntryService.getAllEntries(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<FoodDiaryEntryDto>> getEntriesForSpecificDate(@PathVariable Long id,
                                                                             @RequestParam(required = false, name = "date")
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                             LocalDate date) {
        List<FoodDiaryEntry> entries = foodDiaryEntryService.getEntriesForSpecificDate(id, date);
        List<FoodDiaryEntryDto> entryDtos = entries.stream()
                .map(foodDiaryEntryService::convertToDto)
                .toList();
        return new ResponseEntity<>(entryDtos, HttpStatus.OK);
    }
}
