package com.brow.caloriescalc.repository;

import com.brow.caloriescalc.model.FoodDiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<FoodDiaryEntry, Long> {
}
