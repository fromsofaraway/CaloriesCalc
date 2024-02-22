package com.brow.caloriescalc.controller;

import com.brow.caloriescalc.core.calculator.Calculator;
import com.brow.caloriescalc.core.calculator.Intake;
import com.brow.caloriescalc.dto.IntakeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculate")
public class CalcController {

    private Calculator calculator;

    @Autowired
    public CalcController(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntakeDto> calculateForSingleUser(@PathVariable Long id) {
        Intake intake = calculator.calculate(id);
        IntakeDto intakeDto = calculator.convertToDto(intake);
        return new ResponseEntity<>(intakeDto, HttpStatus.OK);
    }
}
