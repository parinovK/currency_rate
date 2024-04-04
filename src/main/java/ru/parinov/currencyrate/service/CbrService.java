package ru.parinov.currencyrate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.parinov.currencyrate.client.HttpRequest;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CbrService {
    private final HttpRequest request;

    public String requestByCurrencyCode(String code, LocalDate date) {
        return request.requestByCurrencyCode(code, date);
    }
}
