package ru.parinov.currencyrate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.parinov.currencyrate.service.CbrService;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/currency")
@RequiredArgsConstructor

public class CurrencyController {
    private final CbrService service;
    @GetMapping("/{code}")
    public String getCurrencyRate(@PathVariable String code){
        return service.requestByCurrencyCode(code.toUpperCase(), LocalDate.now());//charCode, Time
    }
}
