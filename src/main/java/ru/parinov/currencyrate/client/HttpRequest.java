package ru.parinov.currencyrate.client;

import java.time.LocalDate;

public interface HttpRequest {
    Double requestByCurrencyCode(String charCode, LocalDate date);
    String requestByDate(LocalDate date);
}
