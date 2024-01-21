package com.brow.caloriescalc.service;

import com.brow.caloriescalc.model.FoodDiaryEntry;
import com.brow.caloriescalc.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;

    @Autowired
    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public List<FoodDiaryEntry> getAllDiaryEntries() {
        return diaryRepository.findAll();
    }

    public FoodDiaryEntry saveDiaryEntry(FoodDiaryEntry entry) {
        return diaryRepository.save(entry);
    }
}


