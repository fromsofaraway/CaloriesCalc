package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.core.calculator.Calculator;
import com.brow.caloriescalc.core.calculator.Intake;
import com.brow.caloriescalc.dto.IntakeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/calculate")
public class CalcController {

    private Calculator calculator;

    @Autowired
    public CalcController(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntakeDto> calculate(@PathVariable Long id,
                                               @RequestParam(required = false, name = "date")
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                               LocalDate date) {
        Intake intake;
        if (date != null) {
            intake = calculator.calculateForSpecificDay(id, date);
        } else {
            intake = calculator.calculateForCurrentBusinessDay(id);
        }
        IntakeDto intakeDto = calculator.convertToDto(intake);
        return new ResponseEntity<>(intakeDto, HttpStatus.OK);
    }

}
