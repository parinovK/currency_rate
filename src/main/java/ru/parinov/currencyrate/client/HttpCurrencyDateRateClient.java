package ru.parinov.currencyrate.client;

import java.time.LocalDate;

public interface HttpCurrencyDateRateClient {
    String requestByDate(LocalDate date);
}
