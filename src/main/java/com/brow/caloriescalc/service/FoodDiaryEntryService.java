package com.brow.caloriescalc.service;

import com.brow.caloriescalc.dto.FoodDiaryEntryDto;
import com.brow.caloriescalc.exception.ResourceNotFoundException;
import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.model.Product;
import com.brow.caloriescalc.model.User;
import com.brow.caloriescalc.repository.FoodDiaryEntryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FoodDiaryEntryService {

    private FoodDiaryEntryRepository foodDiaryEntryRepository;

    private UserService userService;

    private ProductService productService;

    private ModelMapper modelMapper;

    @Autowired
    public FoodDiaryEntryService(FoodDiaryEntryRepository foodDiaryEntryRepository, UserService userService, ProductService productService, ModelMapper modelMapper) {
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
        this.userService = userService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    public FoodDiaryEntry createEntry(FoodDiaryEntryDto entryDto) {
        FoodDiaryEntry entry = new FoodDiaryEntry();
        User user = userService.getUserById(entryDto.getUserId());
        Product product = productService.getProductById(entryDto.getProductId());

        entry.setUser(user);
        entry.setProduct(product);
        entry.setAmount(entryDto.getAmount());
        entry.setConsumptionTime(ZonedDateTime.now());

        return foodDiaryEntryRepository.save(entry);
    }

    public List<FoodDiaryEntryDto> getAllEntries() {
        List<FoodDiaryEntry> entries = foodDiaryEntryRepository.findAll();

        List<FoodDiaryEntryDto> entryDtos = entries.stream()
                .map(entry -> modelMapper.map(entry, FoodDiaryEntryDto.class))
                .collect(Collectors.toList());

        return entryDtos;
    }

    public List<FoodDiaryEntry> getEntriesForCurrentBusinessDay(Long userId, ZonedDateTime startOfDay, ZonedDateTime endOfDay) {
        List<FoodDiaryEntry> businessDayEntries = foodDiaryEntryRepository.findByUserIdAndConsumptionTimeBetween(userId, startOfDay, endOfDay);
        return businessDayEntries;
    }
}
