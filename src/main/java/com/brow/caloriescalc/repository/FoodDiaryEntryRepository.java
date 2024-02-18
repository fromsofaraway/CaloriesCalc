package com.brow.caloriescalc.repository;

import com.brow.caloriescalc.model.FoodDiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface FoodDiaryEntryRepository extends JpaRepository<FoodDiaryEntry, Long> {
    List<FoodDiaryEntry> findByUserIdAndConsumptionTimeBetween(Long userId, ZonedDateTime startOfDay, ZonedDateTime endOfDay);
}
