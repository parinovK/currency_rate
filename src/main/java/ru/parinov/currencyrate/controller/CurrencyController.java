package ru.parinov.currencyrate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.parinov.currencyrate.service.CbrService;

import java.time.LocalDate;

//отрефакторить парсер xml в репозитории
//хранить не в стринге, а в числе

@RestController
@RequestMapping("api/v1/currency")
@RequiredArgsConstructor

public class CurrencyController {
    private final CbrService service;
    @GetMapping("/{code}")
    public Double getCurrencyRate(@PathVariable String code){
        return service.requestByCurrencyCode(code.toUpperCase(), LocalDate.now());//charCode, Time
    }
}
