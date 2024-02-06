package com.brow.caloriescalc.service;

import com.brow.caloriescalc.dto.FoodDiaryEntryDto;
import com.brow.caloriescalc.exception.ResourceNotFoundException;
import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.model.Product;
import com.brow.caloriescalc.model.User;
import com.brow.caloriescalc.repository.FoodDiaryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class FoodDiaryEntryService {

    private FoodDiaryEntryRepository foodDiaryEntryRepository;

    private UserService userService;

    private ProductService productService;

    @Autowired
    public FoodDiaryEntryService(FoodDiaryEntryRepository foodDiaryEntryRepository, UserService userService, ProductService productService) {
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public FoodDiaryEntry createEntry(FoodDiaryEntryDto entryDto) {
        User user = userService.getUserById(entryDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id = " + entryDto.getUserId() + " not found"));
        Product product = productService.getProductById(entryDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id = " + entryDto.getProductId() + " not found"));

        return new FoodDiaryEntry(user, product, entryDto.getAmount(), LocalDateTime.now());
    }
}
