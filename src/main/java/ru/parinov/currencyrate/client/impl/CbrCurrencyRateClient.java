package ru.parinov.currencyrate.client.impl;

import org.springframework.stereotype.Component;
import ru.parinov.currencyrate.client.HttpCurrencyDateRateClient;

import java.time.LocalDate;

@Component
public class CbrCurrencyRateClient implements HttpCurrencyDateRateClient {
    @Override
    public String requestByDate(LocalDate date) {
        var baseUrl = "https://www.cbr.ru/scripts/XML_daily.asp";
        return null;
    }
}
