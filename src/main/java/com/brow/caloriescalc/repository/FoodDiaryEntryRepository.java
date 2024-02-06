package com.brow.caloriescalc.repository;

import com.brow.caloriescalc.model.FoodDiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDiaryEntryRepository extends JpaRepository<FoodDiaryEntry, Long> {
}
