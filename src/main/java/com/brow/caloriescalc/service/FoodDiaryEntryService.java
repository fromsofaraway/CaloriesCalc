package com.brow.caloriescalc.service;

import com.brow.caloriescalc.dto.FoodDiaryEntryDto;
import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.model.Product;
import com.brow.caloriescalc.model.User;
import com.brow.caloriescalc.repository.FoodDiaryEntryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing food diary entries.
 */
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

    public List<FoodDiaryEntry> getEntriesForSpecificDate(Long userId, LocalDate date) {
        ZoneId userTimeZone = getUserTimeZone(userId);
        ZonedDateTime startOfDay = date.atStartOfDay(userTimeZone);
        ZonedDateTime endOfDay = startOfDay.plusDays(1);

        return foodDiaryEntryRepository.findByUserIdAndConsumptionTimeBetween(userId, startOfDay, endOfDay);
    }

    private ZoneId getUserTimeZone(Long userId) {
        User user = userService.getUserById(userId);
        return user.getTimezone();
    }

    public FoodDiaryEntryDto convertToDto(FoodDiaryEntry entry) {
        return modelMapper.map(entry, FoodDiaryEntryDto.class);
    }
}
