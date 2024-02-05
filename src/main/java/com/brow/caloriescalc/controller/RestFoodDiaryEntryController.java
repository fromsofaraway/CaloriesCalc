package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.dto.FoodDiaryEntryDto;
import com.brow.caloriescalc.exception.ResourceNotFoundException;
import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.model.Product;
import com.brow.caloriescalc.model.User;
import com.brow.caloriescalc.service.DiaryService;
import com.brow.caloriescalc.service.ProductService;
import com.brow.caloriescalc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diary")
public class RestFoodDiaryEntryController {

    private UserService userService;

    private DiaryService diaryService;

    private ProductService productService;

    @Autowired
    public RestFoodDiaryEntryController(UserService userService, DiaryService diaryService, ProductService productService) {
        this.userService = userService;
        this.diaryService = diaryService;
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<FoodDiaryEntry> createDiaryEntry(FoodDiaryEntryDto entryDto) {
        User user = userService.getUserById(entryDto
                .getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id = " + entryDto.getUserId() + " not found"));
        Product product = productService.getProductById(entryDto
                .getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id = " + entryDto.getProductId() + " not found"));

        FoodDiaryEntry savedEntry = new FoodDiaryEntry(user, product, entryDto.getAmount(), entryDto.getConsumptionTime());
        diaryService.saveDiaryEntry(savedEntry);
        return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
    }

}
